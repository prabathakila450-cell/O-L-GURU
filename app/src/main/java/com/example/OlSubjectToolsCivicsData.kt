package com.example

object OlSubjectToolsCivicsData {
  val tools: List<OlSubjectToolItem> = listOf(
    civTool("civ_001", 1, 1, "ආණ්ඩුක්‍රම ව්‍යවස්ථාව සහ මූලික අයිතිවාසිකම්", "Constitution & Fundamental Rights", "రాජ්‍ය විද්‍යාව", "⚖️", "ශ්‍රී ලංකා ප්‍රජාතාන්ත්‍රික සමාජවාදී ජනරජයේ ආණ්ඩුක්‍රම ව්‍යවස්ථාව (1978).", "III වන පරිච්ඡේදයේ සඳහන් මූලික අයිතිවාසිකම් (සමානාත්මතාවය, භාෂණයේ නිදහස).", "පුරවැසි අයිතිවාසිකම් ආරක්ෂා කර ගැනීම.", "මූලික අයිතිවාසිකම් කඩ වූ විට ශ්‍රේෂ්ඨාධිකරණයට යා හැකිය."),
    civTool("civ_002", 2, 1, "ಸರ್වකාලීන ආණ්ඩු ක්‍රමයේ අංශ 3", "Three Organs of Government", "පාලන ක්‍රමය", "🏛️", "ව්‍යවස්ථාදායකය (පාර්ලිමේන්තුව), විධායකය (ජනාධිපති හා කැබინට් මණ්ඩලය), අධිකරණය.", "බල බෙදීම (Separation of Powers) මගින් ආණ්ඩු බලය අයුතු ලෙස භාවිතය වැළැක්වීම.", "ප්‍රජාතාන්ත්‍රික පාලනය පවත්වා ගැනීම.", "පාර්ලිමේන්තුව නීති සාදන අතර අධිකරණය නීතිය ක්‍රියාත්මක කරයි."),
    civTool("civ_003", 3, 1, "මැතිවරණ ක්‍රමය සහ ප්‍රජාතන්ත්‍රවාදය", "Electoral System & Democracy", "මැතිවරණ", "🗳️", "සර්වජන ඡන්ද බලය සහ සමානුපාතික නියෝජන ක්‍රමය.", "වයස අවුරුදු 18 පිරුණු සෑම පුරවැසියකුටම හිමි ඡන්ද අයිතිය.", "ජනතා නියෝජිතයන් තෝරා පත් කර ගැනීම.", "1931 ඩොනමෝර් ක්‍රමයෙන් ලංකාවට සර්වජන ඡන්ද බලය ලැබිණි.")
  )

  private fun civTool(id: String, num: Int, grp: Int, si: String, en: String, cat: String, ic: String, sum: String, form: String, prac: String, tip: String): OlSubjectToolItem {
    return OlSubjectToolItem(id, "පුරවැසි අධ්‍යයනය", num, grp, si, en, cat, ic, sum, form, prac, tip)
  }
}
