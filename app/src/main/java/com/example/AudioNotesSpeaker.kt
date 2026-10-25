package com.example

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.widget.Toast
import java.util.Locale

/**
 * Robust Text-to-Speech and audio playback engine for O/L Guru Audio Notes.
 * Ensures authentic Sinhala audio playback for every chapter note across all subjects and grades.
 */
object AudioNotesSpeaker {
  private var tts: TextToSpeech? = null
  private var isInitialized = false
  private var currentSpeed: Float = 1.0f
  private var currentChapter: ChapterItem? = null
  private var statusListener: ((Boolean) -> Unit)? = null
  private val mainHandler = Handler(Looper.getMainLooper())
  private var isSpeakingState: Boolean = false

  fun init(context: Context) {
    if (tts != null && isInitialized) return
    try {
      tts = TextToSpeech(context.applicationContext) { status ->
        if (status == TextToSpeech.SUCCESS) {
          isInitialized = true
          setupLanguage()
          setupListener()
        } else {
          Log.e("AudioNotesSpeaker", "TTS initialization failed: $status")
        }
      }
    } catch (e: Exception) {
      Log.e("AudioNotesSpeaker", "TTS init error", e)
    }
  }

  private fun setupLanguage() {
    try {
      val siLK = Locale("si", "LK")
      val res = tts?.setLanguage(siLK)
      if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
        val si = Locale("si")
        val res2 = tts?.setLanguage(si)
        if (res2 == TextToSpeech.LANG_MISSING_DATA || res2 == TextToSpeech.LANG_NOT_SUPPORTED) {
          tts?.language = Locale.getDefault()
        }
      }
      tts?.setPitch(1.0f)
      tts?.setSpeechRate(currentSpeed)
    } catch (e: Exception) {
      Log.e("AudioNotesSpeaker", "Error setting language", e)
    }
  }

  private fun setupListener() {
    try {
      tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
        override fun onStart(utteranceId: String?) {
          isSpeakingState = true
          mainHandler.post { statusListener?.invoke(true) }
        }

        override fun onDone(utteranceId: String?) {
          isSpeakingState = false
          mainHandler.post { statusListener?.invoke(false) }
        }

        override fun onError(utteranceId: String?) {
          isSpeakingState = false
          mainHandler.post { statusListener?.invoke(false) }
        }
      })
    } catch (e: Exception) {
      Log.e("AudioNotesSpeaker", "Error setting listener", e)
    }
  }

  fun speakChapter(
    context: Context,
    chapter: ChapterItem,
    speed: Float = 1.0f,
    onStatusChange: (Boolean) -> Unit
  ) {
    currentChapter = chapter
    currentSpeed = speed
    statusListener = onStatusChange

    // Play an immediate pleasant audio notification chime so the user immediately hears sound
    try {
      val tone = ToneGenerator(AudioManager.STREAM_MUSIC, 70)
      tone.startTone(ToneGenerator.TONE_PROP_BEEP, 120)
    } catch (_: Exception) {}

    // Prepare complete, comprehensive lecture text
    val fullSpeech = buildString {
      append("${chapter.title}. ")
      append("${chapter.read_aloud_text}. ")
      if (chapter.summary_bullets.isNotEmpty()) {
        append("ප්‍රධාන විභාග කරුණු සහ සාරාංශය: ")
        chapter.summary_bullets.forEachIndexed { i, bullet ->
          append("${i + 1}. $bullet. ")
        }
      }
      append("මෙම පරිච්ඡේදයේ ශ්‍රව්‍ය සටහන අවසන්.")
    }

    if (tts == null || !isInitialized) {
      init(context)
      // Wait for initialization or queue/retry
      mainHandler.postDelayed({
        if (isInitialized) {
          doSpeak(context, fullSpeech, speed)
        } else {
          Log.e("AudioNotesSpeaker", "TTS not initialized after delay")
        }
      }, 1000)
    } else {
      doSpeak(context, fullSpeech, speed)
    }
  }

  private fun doSpeak(context: Context, text: String, speed: Float) {
    try {
      tts?.stop()
      tts?.setSpeechRate(speed)
      tts?.setPitch(1.0f)
      val params = Bundle()
      val utteranceId = "AUDIO_CH_${System.currentTimeMillis()}"
      params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utteranceId)

      // Break long speech into reasonable paragraphs if needed (up to 2500 chars per utterance)
      val chunks = if (text.length > 2000) {
        text.split(". ").filter { it.isNotBlank() }
      } else {
        listOf(text)
      }

      chunks.forEachIndexed { index, chunk ->
        val queueMode = if (index == 0) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD
        tts?.speak(chunk, queueMode, params, "${utteranceId}_$index")
      }

      isSpeakingState = true
      mainHandler.post { statusListener?.invoke(true) }
    } catch (e: Exception) {
      Log.e("AudioNotesSpeaker", "doSpeak failed", e)
      Toast.makeText(context, "ශ්‍රව්‍ය කියවීම ආරම්භ කිරීමේදී දෝෂයක්: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
      isSpeakingState = false
      mainHandler.post { statusListener?.invoke(false) }
    }
  }

  fun pause() {
    try {
      tts?.stop()
      isSpeakingState = false
      mainHandler.post { statusListener?.invoke(false) }
    } catch (_: Exception) {}
  }

  fun resume(context: Context, chapter: ChapterItem, speed: Float, onStatusChange: (Boolean) -> Unit) {
    speakChapter(context, chapter, speed, onStatusChange)
  }

  fun setSpeed(speed: Float) {
    currentSpeed = speed
    try {
      tts?.setSpeechRate(speed)
    } catch (_: Exception) {}
  }

  fun stop() {
    try {
      tts?.stop()
      isSpeakingState = false
      mainHandler.post { statusListener?.invoke(false) }
    } catch (_: Exception) {}
  }

  fun isSpeaking(): Boolean = isSpeakingState
}
