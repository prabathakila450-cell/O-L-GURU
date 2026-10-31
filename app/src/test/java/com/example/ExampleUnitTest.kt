package com.example

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun testFlashcards() {
    val grades = listOf("9", "10", "11")
    val subjects = ComprehensiveFlashcardRepository.availableSubjects
    var emptyCount = 0
    for (g in grades) {
      for (sub in subjects) {
        val cards = ComprehensiveFlashcardRepository.getFlashcards(g, sub, null)
        for (c in cards) {
          if (c.questionFront.isBlank() || c.answerBack.isBlank()) {
            println("EMPTY CARD FOUND: Grade $g, Sub $sub, Set ${c.setNumber}, Index ${c.cardIndexInSet}")
            emptyCount++
          }
        }
      }
    }
    println(">>> Done checking all cards! Total empty cards: $emptyCount")
    assertEquals(0, emptyCount)
  }

  @Test
  fun testGrade10MathQuizDistinctness() {
    val allQuestions = mutableListOf<MathQuizQuestion>()
    for (set in 1..80) {
      val questions = MathQuizEngine.getSetQuestions(10, set)
      allQuestions.addAll(questions)
    }
    val duplicates = allQuestions.groupBy { it.questionText }.filter { it.value.size > 1 }
    val uniqueCount = allQuestions.map { it.questionText }.toSet().size
    val firstDup = duplicates.entries.firstOrNull()
    val msg = "Total duplicates: ${duplicates.size}. Unique: $uniqueCount/2000. Example: ${firstDup?.key} in sets ${firstDup?.value?.map { it.setNumber }}"
    assertEquals(msg, 2000, uniqueCount)
  }

  @Test
  fun testMatchingBankIntegrity() {
    val sets = InteractiveMatchingAndTimelineBank.matchSets
    assertEquals("Should have exactly 150 sets (15 subjects x 10 sets)", 150, sets.size)

    val subjects = sets.map { it.subject }.distinct()
    assertEquals("Should have exactly 15 subjects", 15, subjects.size)

    for (subject in subjects) {
      val subjectSets = sets.filter { it.subject == subject }
      assertEquals("Subject $subject must have exactly 10 sets", 10, subjectSets.size)

      val allItems = subjectSets.flatMap { it.items }
      assertEquals("Subject $subject must have exactly 100 items", 100, allItems.size)

      val uniqueQuestions = allItems.map { it.leftText }.distinct()
      assertEquals("Subject $subject must have 100 unique questions", 100, uniqueQuestions.size)

      for (item in allItems) {
        assertTrue("Question cannot be blank in $subject", item.leftText.isNotBlank())
        assertTrue("Answer cannot be blank in $subject", item.rightCorrectText.isNotBlank())
      }
    }
  }
}

