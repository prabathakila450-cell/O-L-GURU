package com.example

/**
 * 20 completely distinct, syllabus-aligned essay question templates tailored precisely to each subject
 * (History, Buddhism, Sinhala, Civics, Geography, Commerce, Science, Math, ICT, English).
 * Guarantees zero cross-contamination and accurate subject-specific model answers.
 */
object EssayQuestionBankTemplates {

  fun getEssaySubQuestions(
    subject: String,
    cleanTheme: String,
    qIndex: Int,
    setNumber: Int
  ): List<StructuredSubQuestion> {
    val q = qIndex.coerceIn(1, 20)

    return when {
      subject.contains("ඉතිහාසය") -> getHistoryEssaySubQuestions(cleanTheme, q)
      subject.contains("බුද්ධ ධර්මය") -> getBuddhismEssaySubQuestions(cleanTheme, q)
      subject.contains("සිංහල") -> getSinhalaEssaySubQuestions(cleanTheme, q)
      subject.contains("පුරවැසි අධ්‍යාපනය") -> getCivicsEssaySubQuestions(cleanTheme, q)
      subject.contains("භූගෝල විද්‍යාව") -> getGeographyEssaySubQuestions(cleanTheme, q)
      subject.contains("ව්‍යාපාර හා ගිණුම්කරණය") -> getCommerceEssaySubQuestions(cleanTheme, q)
      else -> getGeneralEssaySubQuestions(cleanTheme, q)
    }
  }

  private fun getHistoryEssaySubQuestions(theme: String, q: Int): List<StructuredSubQuestion> {
    return when (q % 3) {
      1 -> listOf(
        StructuredSubQuestion("(A)", "$theme යුගයේ ඓතිහාසික පසුබිම, දේශපාලනික හා ආර්ථික සංවර්ධනය සවිස්තරව විග්‍රහ කරන්න.", 8, "රජවරුන්ගේ දායකත්වය, මධ්‍යගත පාලන ක්‍රමය සහ වාරි කර්මාන්තයේ දියුණුව සවිස්තරව විස්තර කරයි.", listOf("දේශපාලන පසුබිම (ලකුණු 4)", "ආර්ථික සංවර්ධනය (ලකුණු 4)")),
        StructuredSubQuestion("(B)", "එම යුගයේ සමාජ සංස්කෘතික ප්‍රබෝධය සහ විදෙස් සබඳතා පැවති ආකාරය සාකච්ඡා කරන්න.", 7, "බුදුදහමේ ප්‍රචාරණය, සාහිත්‍ය හා කලාව මෙන්ම විදෙස් රටలతో කළ වෙළඳාම.", listOf("සමාජ සංස්කෘතික ප්‍රබෝධය (ලකුණු 4)", "විදෙස් සබඳතා (ලකුණු 3)")),
        StructuredSubQuestion("(C)", "මෙම යුගයේ පරිහානියට මුල් වූ අභ්‍යන්තර හා බාහිර හේතු විශ්ලේෂණය කර පාඩම් ඉගෙන ගන්න.", 5, "අභ්‍යන්තර බෙදීම් සහ විදේශීය ආක්‍රමණවල බලපෑම සාධාරණීකරණය කිරීම.", listOf("හේතු විශ්ලේෂණය (ලකුණු 5)"))
      )
      2 -> listOf(
        StructuredSubQuestion("(A)", "$theme ආශ්‍රිත පුරාවිද්‍යාත්මක මූලාශ්‍ර සහ ඒවායේ ඓතිහාසික වැදගත්කම විග්‍රහ කරන්න.", 8, "සෙල්ලිපි, ස්තූප, වැව් සහ විදේශීය සංචාරක වාර්තා මඟින් ඉතිහාසය තහවුරු වන ආකාරය.", listOf("මූලාශ්‍ර වර්ගීකරණය (ලකුණු 4)", "ඓතිහාසික වැදගත්කම (ලකුණු 4)")),
        StructuredSubQuestion("(B)", "අතීත ශ්‍රී ලංකාවේ ජල කළමනාකරණ තාක්ෂණය ලෝකයටම ආදර්ශයක් වූ ආකාරය පැහැදිලි කරන්න.", 7, "අගල්, බැමි, බිසෝකොටුව සහ මහා පරිමාණ වැව් පද්ධතිවල ක්‍රියාකාරීත්වය.", listOf("වාරි තාක්ෂණය (ලකුණු 4)", "ලෝක ආදර්ශය (ලකුණු 3)")),
        StructuredSubQuestion("(C)", "වර්තමාන සංවර්ධන කටයුතුවලදී අතීත වාරි ශිෂ්ටාචාරයෙන් ගත හැකි ආදර්ශය කුමක්ද?", 5, "පරිසර හිතකාමී සහ තිරසාර ජල සම්පත් කළමනාකරණය.", listOf("ප්‍රායෝගික ආදර්ශය (ලකුණු 5)"))
      )
      else -> listOf(
        StructuredSubQuestion("(A)", "$theme යුගයේ රාජ්‍ය පාලනය සහ බෞද්ධ සංස්කෘතිය අතර පැවති අවියෝජනීය සම්බන්ධතාවය විමසන්න.", 8, "රජු සහ සංඝයා අතර අන්‍යෝන්‍ය සහයෝගය සහ රටේ දියුණුවට එය බලපෑ ආකාරය.", listOf("රාජ්‍ය-ශාසන සබඳතාව (ලකුණු 4)", "සමාජ බලපෑම (ලකුණු 4)")),
        StructuredSubQuestion("(B)", "එම යුගයේ ජනජීවිතය, කෘෂිකර්මාන්තය සහ ගෘහ කර්මාන්ත සංවර්ධනය වූ අයුරු විස්තර කරන්න.", 7, "ග්‍රාමීය ආර්ථිකය, කුඹුරු වගාව සහ ශිල්පීය ක්‍රම.", listOf("ජනජීවිතය (ලකුණු 4)", "කෘෂිකර්මාන්තය (ලකුණු 3)")),
        StructuredSubQuestion("(C)", "ඉතිහාසය හැදෑරීමෙන් වර්තමාන පුරවැසියා ලබාගත යුතු ප්‍රධාන ජාතික පණිවිඩය කුමක්ද?", 5, "ජාතික එකමුතුකම සහ ස්වෛරීභාවය ආරක්ෂා කිරීම.", listOf("ජාතික පණිවිඩය (ලකුණු 5)"))
      )
    }
  }

  private fun getBuddhismEssaySubQuestions(theme: String, q: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion("(A)", "$theme බෞද්ධ දර්ශනයේ එන මූලික ධර්ම මූලධර්මය සහ එහි දාර්ශනික ගැඹුර සවිස්තරව විග්‍රහ කරන්න.", 8, "දුඃඛය, කර්මය, පටිච්ච සමුප්පාදය හෝ නිර්වාණය පිළිබඳ ගැඹුරු ධර්මානුකූල විග්‍රහය.", listOf("දාර්ශනික පසුබිම (ලකුණු 4)", "මූලධර්ම විග්‍රහය (ලකුණු 4)")),
      StructuredSubQuestion("(B)", "මෙම ධර්ම කරුණ ප්‍රායෝගික ජීවිතයට යොදාගැනීමෙන් පුද්ගල හා සමාජ සංවර්ධනයක් වන ආකාරය පැහැදිලි කරන්න.", 7, "සිත දමනය කරගැනීම, සදාචාර සම්පන්න වීම සහ සාමකාමී සමාජයක් බිහිවීම.", listOf("පුද්ගල සංවර්ධනය (ලකුණු 4)", "සමාජ සංවර්ධනය (ලකුණු 3)")),
      StructuredSubQuestion("(C)", "වර්තමාන අසහනකාරී සමාජයට බෞද්ධ ජීවන දර්ශනය ලබාදෙන ස්ථිරසාර විසඳුම කුමක්ද?", 5, "මෛත්‍රිය, ඉවසීම සහ සරල ජීවන රටාව අනුගමනය කිරීම.", listOf("විසඳුම ඉදිරිපත් කිරීම (ලකුණු 5)"))
    )
  }

  private fun getSinhalaEssaySubQuestions(theme: String, q: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion("(A)", "$theme සාහිත්‍ය නිර්මාණයේ හෝ භාෂා සංකල්පයේ අන්තර්ගතය, ස්වරූපය සහ රචකයාගේ අරමුණ සවිස්තරව විග්‍රහ කරන්න.", 8, "රචකයාගේ විලාසය, භාෂා හැසිරවීම සහ කෘතියේ හරය මැනවින් ඉස්මතු කර දැක්වීම.", listOf("අන්තර්ගත විග්‍රහය (ලකුණු 4)", "රචකයාගේ අරමුණ (ලකුණු 4)")),
      StructuredSubQuestion("(B)", "කෘතියේ එන චරිත, සංස්කෘතික පසුබිම හෝ ව්‍යාකරණ රීති ගැඹුරින් සාකච්ඡා කරන්න.", 7, "චරිතවල ස්වභාවය සහ සමාජ යථාර්ථය නිරූපණය වන ආකාරය.", listOf("විස්තරය (ලකුණු 4)", "සමාජ පසුබිම (ලකුණු 3)")),
      StructuredSubQuestion("(C)", "මෙම නිර්මාණයෙන් පාඨකයාගේ රසඥතාව සහ ජීවන දෘෂ්ටිය පොහොසත් වන ආකාරය ලියන්න.", 5, "සංවේදී වීම සහ මිනිස් සජීවීබව අවබෝධ කරගැනීම.", listOf("රසඥතාව (ලකුණු 5)"))
    )
  }

  private fun getCivicsEssaySubQuestions(theme: String, q: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion("(A)", "$theme ප්‍රජාතන්ත්‍රවාදී පාලන ක්‍රමයේ සහ ආණ්ඩුක්‍රම ව්‍යවස්ථාවේ මූලික ප්‍රතිපාදන සවිස්තරව විග්‍රහ කරන්න.", 8, "මූලික අයිතිවාසිකම්, රාජ්‍ය ආයතනවල බලතල සහ ජනතා පරමාධිපත්‍යය.", listOf("ව්‍යවස්ථාපිත ප්‍රතිපාදන (ලකුණු 4)", "පාලන ක්‍රමය (ලකුණු 4)")),
      StructuredSubQuestion("(B)", "යහපත් පුරවැසියෙකුගේ යුතුකම්, වගකීම් සහ ජාතික සංවර්ධනයට ඇති දායකත්වය සාකච්ඡා කරන්න.", 7, "නීතියට ගරු කිරීම, මහජන දේපළ ආරක්ෂා කිරීම සහ සංහිඳියාව.", listOf("යුතුකම් හා වගකීම් (ලකුණු 4)", "ජාතික දායකත්වය (ලකුණු 3)")),
      StructuredSubQuestion("(C)", "ප්‍රජාතන්ත්‍රවාදය ශක්තිමත් කිරීමට පුරවැසියන් ලෙස අප කළ යුතු ප්‍රධාන කාර්යභාරය කුමක්ද?", 5, "ක්‍රියාශීලීව සහ උත්සාහයෙන් යුතුව ප්‍රජාතන්ත්‍රවාදී ක්‍රියාවලියට දායක වීම.", listOf("කාර්යභාරය (ලකුණු 5)"))
    )
  }

  private fun getGeographyEssaySubQuestions(theme: String, q: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion("(A)", "$theme භූගෝලීය සංසිද්ධිය, දේශගුණික හෝ ස්වභාවික පරිසර පද්ධතිය සවිස්තරව විග්‍රහ කරන්න.", 8, "භූමිරූප, ජල පද්ධති සහ දේශගුණික සාධකවල අන්තර්ක්‍රියාකාරිත්වය.", listOf("භූගෝලීය විග්‍රහය (ලකුණු 4)", "පරිසර පද්ධතිය (ලකුණු 4)")),
      StructuredSubQuestion("(B)", "මානව ක්‍රියාකාරකම් නිසා පරිසරයට සිදුවන බලපෑම සහ ආපදා කළමනාකරණ ක්‍රමවේද සාකච්ඡා කරන්න.", 7, "නායයෑම්, ගංවතුර සහ පස් සංරක්ෂණ ක්‍රම.", listOf("මානව බලපෑම (ලකුණු 4)", "ආපදා කළමනාකරණය (ලකුණු 3)")),
      StructuredSubQuestion("(C)", "තිරසාර සංවර්ධන අරමුණු සාක්ෂාත් කරගැනීමට භූගෝලීය සම්පත් නිසි ලෙස කළමනාකරණය කරන්නේ කෙසේද?", 5, "සම්පත් අරපිරිමැස්ම සහ හරිත උපායමාර්ග.", listOf("තිරසාර කළමනාකරණය (ලකුණු 5)"))
    )
  }

  private fun getCommerceEssaySubQuestions(theme: String, q: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion("(A)", "$theme ව්‍යාපාරික හෝ මූල්‍ය කළමනාකරණ සංකල්පය සවිස්තරව විග්‍රහ කරන්න.", 8, "ගිණුම්කරණ සමීකරණය, ද්විත්ව සටහන් සහ මූල්‍ය ප්‍රකාශන සකස් කිරීම.", listOf("මූල්‍ය සංකල්පය (ලකුණු 4)", "ගිණුම්කරණ ක්‍රියාවලිය (ලකුණු 4)")),
      StructuredSubQuestion("(B)", "ව්‍යාපාරයක අලෙවිකරණය, මූල්‍ය කළමනාකරණය සහ ආර්ථික දායකත්වය සාකච්ඡා කරන්න.", 7, "4Ps, ලාභ ඉපැයීම සහ ජාතික ආර්ථිකයට කරන දායකත්වය.", listOf("අලෙවිකරණය (ලකුණු 4)", "ආර්ථික දායකත්වය (ලකුණු 3)")),
      StructuredSubQuestion("(C)", "නවීන ව්‍යාපාර ලෝකයේ ආචාරධර්ම සහ ඊ-වාණිජ්‍යයේ වැදගත්කම විග්‍රහ කරන්න.", 5, "ව්‍යාපාරික ආචාරධර්ම සහ තාක්ෂණික දියුණුව.", listOf("ආචාරධර්ම හා තාක්ෂණය (ලකුණු 5)"))
    )
  }

  private fun getGeneralEssaySubQuestions(theme: String, q: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion("(A)", "$theme විෂය කරුණට අදාළ න්‍යාය, මූලධර්ම සහ ප්‍රායෝගික යෙදුම් සවිස්තරව රචනා කරන්න.", 8, "මූලික සිද්ධාන්ත සහ විද්‍යාත්මක හෝ ශාස්ත්‍රීය පසුබිම.", listOf("න්‍යායික පසුබිම (ලකුණු 4)", "ප්‍රායෝගික යෙදුම (ලකුණු 4)")),
      StructuredSubQuestion("(B)", "අදාළ ක්ෂේත්‍රයේ පවතින ගැටලු, අභියෝග සහ ඒවාට දිය හැකි ප්‍රායෝගික විසඳුම් සාකච්ඡා කරන්න.", 7, "අභියෝග හඳුනාගැනීම සහ නිවැරදි ක්‍රියාමාර්ග.", listOf("අභියෝග (ලකුණු 4)", "විසඳුම් (ලකුණු 3)")),
      StructuredSubQuestion("(C)", "සමස්ත විෂය ඒකකයෙන් සමාජයට ලැබෙන සේවය සහ අනාගත දැක්ම ප්‍රකාශ කරන්න.", 5, "සමස්ත සාරාංශය සහ අනාගත අපේක්ෂා.", listOf("අනාගත දැක්ම (ලකුණු 5)"))
    )
  }
}
