package com.example

import android.app.Application
import android.os.Build
import android.webkit.WebView
import java.io.File

class StudentPortalApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    // Pre-create Chromium WebView cache and code cache directories
    // to prevent simple_backend_impl.cc directory creation errors
    try {
      val webViewDefault = File(cacheDir, "WebView/Default")
      val httpCache = File(webViewDefault, "HTTP Cache")
      val codeCache = File(httpCache, "Code Cache")
      File(codeCache, "wasm").mkdirs()
      File(codeCache, "js").mkdirs()
    } catch (_: Exception) {}

    // Multi-process safety for WebView in Android 9+ (API 28+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      try {
        val processName = getProcessName()
        if (packageName != processName) {
          WebView.setDataDirectorySuffix(processName)
        }
      } catch (_: Exception) {}
    }
  }
}
