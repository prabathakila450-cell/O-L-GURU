package com.example

object OlSubjectToolsHealthData {
  val tools: List<OlSubjectToolItem> = listOf(
    healTool("heal_001", 1, 1, "සමබල ආහාර වේලක් සැකසීම", "Balanced Diet Formulation", "පෝෂණය", "🥗", "කාබෝහයිඩ්‍රේට්, ප්‍රෝටීන්, මේද, විටමින්, ඛනිජ ලවණ සහ ජලය නිසි අනුපාතයට ගැනීම.", "ශරීරයේ දෛනික ශක්ති අවශ්‍යතාවය පවත්වා ගැනීම.", "නිරෝගී සෞඛ්‍ය සම්පන්න ජීවන රටාවක් ගත කිරීම.", "විටමින් ඌනතා රෝග O/L විභාගයේදී නිතර අසයි."),
    healTool("heal_002", 2, 1, "මානසික සෞඛ්‍යය සහ ආතති කළමනාකරණය", "Mental Health & Stress Management", "മാനසික සෞඛ්‍යය", "🧘", "මානසික ආතතිය පාලනය කරගැනීමේ ක්‍රම, භාවනාව, ක්‍රීඩා සහ විවේකය.", "මනසේ සතුට හා සමාජීය යහපැවැත්ම.", "විභාග ආතතිය පාලනය කර ගැනීම.", "හොඳ නින්දක් සහ විවේකයක් මනසේ ප්‍රබෝධයට හේුවේ.")
  )

  private fun healTool(id: String, num: Int, grp: Int, si: String, en: String, cat: String, ic: String, sum: String, form: String, prac: String, tip: String): OlSubjectToolItem {
    return OlSubjectToolItem(id, "සෞඛ්‍යය", num, grp, si, en, cat, ic, sum, form, prac, tip)
  }
}
