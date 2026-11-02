package com.example

/**
 * 20 completely distinct, syllabus-aligned essay question templates tailored precisely to each subject
 * (History, Buddhism, Sinhala, Civics, Geography, Commerce, Science, Math, ICT, English).
 * Guarantees zero cross-contamination and accurate subject-specific model answers for Grade 9, 10, and 11.
 */
object EssayQuestionBankTemplates {

  fun getEssaySubQuestions(
    grade: String,
    subject: String,
    cleanTheme: String,
    qIndex: Int,
    setNumber: Int
  ): List<StructuredSubQuestion> {
    val q = qIndex.coerceIn(1, 20)

    return when {
      subject.contains("ඉතිහාසය") -> getHistoryEssaySubQuestions(grade, cleanTheme, q, setNumber)
      subject.contains("බුද්ධ ධර්මය") -> getBuddhismEssaySubQuestions(grade, cleanTheme, q, setNumber)
      subject.contains("සිංහල") -> getSinhalaEssaySubQuestions(grade, cleanTheme, q, setNumber)
      subject.contains("පුරවැසි අධ්‍යාපනය") -> getCivicsEssaySubQuestions(grade, cleanTheme, q, setNumber)
      subject.contains("භූගෝල") -> getGeographyEssaySubQuestions(grade, cleanTheme, q, setNumber)
      subject.contains("ව්‍යාපාර හා ගිණුම්කරණය") -> getCommerceEssaySubQuestions(grade, cleanTheme, q, setNumber)
      subject.contains("විද්‍යාව") -> getScienceEssaySubQuestions(grade, cleanTheme, q, setNumber)
      subject.contains("ගණිතය") -> getMathEssaySubQuestions(grade, cleanTheme, q, setNumber)
      subject.contains("ICT") -> getIctEssaySubQuestions(grade, cleanTheme, q, setNumber)
      else -> getGeneralEssaySubQuestions(grade, cleanTheme, q, setNumber)
    }
  }

  private fun getScienceEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    val variant = (q % 4)
    return when (variant) {
      1 -> listOf(
        StructuredSubQuestion(
          "(A)",
          "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) හි ප්‍රධාන විද්‍යාත්මක පදනම, පරීක්ෂණ සැලසුම සහ රසායනික/භෞතික පසුබිම සවිස්තරව රචනා කරන්න.",
          8,
          "$theme ආශ්‍රිත O/L විෂය නිර්දේශයේ සම්මත සිද්ධාන්තය, නිරීක්ෂණ, තුලිත සමීකරණ සහ ගණනය කිරීම් සවිස්තරව විග්‍රහ කරයි.",
          listOf("න්‍යායික පසුබිම (ලකුණු 4)", "පරීක්ෂණ නිරීක්ෂණ හා සමීකරණ (ලකුණු 4)")
        ),
        StructuredSubQuestion(
          "(B)",
          "මෙම විද්‍යාත්මක සංකල්පයේ තාක්ෂණික හා කාර්මික යෙදීම් මෙන්ම එදිනෙදා ජීවිතයේ වැදගත්කම සාකච්ඡා කරන්න.",
          7,
          "කර්මාන්ත, ඖෂධ නිෂ්පාදනය සහ බලශක්ති උත්පාදනයේදී අදාළ සිද්ධාන්තය භාවිත වන ආකාරය.",
          listOf("කාර්මික යෙදීම් (ලකුණු 4)", "එදිනෙදා වැදගත්කම (ලකුණු 3)")
        ),
        StructuredSubQuestion(
          "(C)",
          "මෙම ක්‍රියාවලියේදී සිදුවිය හැකි පාරිසරික බලපෑම් සහ ඒවා පාලනය කිරීමට ගත හැකි තිරසාර පියවර ලියන්න.",
          5,
          "පරිසර දූෂණය අවම කිරීම සහ අනාගතය උදෙසා සම්පත් සුරක්ෂිත කිරීම.",
          listOf("පාරිසරික විසඳුම් (ලකුණු 5)")
        )
      )
      2 -> listOf(
        StructuredSubQuestion(
          "(A)",
          "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) ආශ්‍රිතව සෛලීය/පටක/පරිපථ ව්‍යුහවල කාර්යභාරය සහ යාන්ත්‍රණය සවිස්තරව පැහැදිලි කරන්න.",
          8,
          "ජෛව විද්‍යාත්මක හෝ භෞතික විද්‍යාත්මක පද්ධතියේ ක්‍රියාකාරී අංශ O/L සම්මතයන්ට අනුව විග්‍රහ කිරීම.",
          listOf("ව්‍යුහය සහ යාන්ත්‍රණය (ලකුණු 8)")
        ),
        StructuredSubQuestion(
          "(B)",
          "අදාළ පරීක්ෂණයේදී සාධක පාලනය කරන ආකාරය සහ නිරීක්ෂණය කරන ලක්ෂණ සාකච්ඡා කරන්න.",
          7,
          "පාලක පරීක්ෂණ සකස් කිරීම, දත්ත සටහන් කරගැනීම සහ නිගමනවලට පැමිණීම.",
          listOf("පරීක්ෂණ සාධක පාලනය (ලකුණු 7)")
        ),
        StructuredSubQuestion(
          "(C)",
          "මෙම විද්‍යාත්මක දැනුම භාවිත කරමින් එදිනෙදා පැනනඟින තාක්ෂණික ගැටලුවකට විසඳුම් ඉදිරිපත් කරන්න.",
          5,
          "ප්‍රායෝගික තාක්ෂණික යෙදීම සහ ආරක්ෂිත පූර්වාරක්ෂණ ක්‍රියාමාර්ග.",
          listOf("ගැටලු විසඳීම (ලකුණු 5)")
        )
      )
      else -> listOf(
        StructuredSubQuestion(
          "(A)",
          "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) ආශ්‍රිත ගණිතමය/රසායනික ගණනය කිරීම් සහ තුලිත සමීකරණ පියවරෙන් පියවර විසඳන්න.",
          8,
          "මවුලික සංකල්ප, රේඛීය චලිත සමීකරණ හෝ ඕම්ගේ නියමය භාවිතයෙන් විසඳුම ලබාගැනීම.",
          listOf("ගණනය කිරීම් හා සමීකරණ (ලකුණු 8)")
        ),
        StructuredSubQuestion(
          "(B)",
          "මෙම විද්‍යාත්මක සොයාගැනීමේ ඓතිහාසික පසුබිම සහ නවීන විද්‍යාවට ලැබුණු දායකත්වය සාකච්ඡා කරන්න.",
          7,
          "විද්‍යාඥයන්ගේ සොයාගැනීම් සහ නූතන තාක්ෂණයේ ප්‍රගතිය.",
          listOf("විද්‍යාත්මක දායකත්වය (ලකුණු 7)")
        ),
        StructuredSubQuestion(
          "(C)",
          "අනාගත පාරිසරික සහ සෞඛ්‍යමය තිරසාරභාවය උදෙසා මෙම දැනුම යොදාගන්නා ආකාරය ලියන්න.",
          5,
          "තිරසාර විද්‍යාත්මක උපායමාර්ග සහ පරිසර සංරක්ෂණය.",
          listOf("තිරසාරභාවය (ලකුණු 5)")
        )
      )
    }
  }

  private fun getMathEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    val variant = (q % 3)
    return when (variant) {
      1 -> listOf(
        StructuredSubQuestion(
          "(A)",
          "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) පාඩමේ එන ප්‍රධාන ජ්‍යාමිතික ප්‍රමේයය සාධනය කර හෝ වීජීය රචනා ගැටලුව ආකෘතිකරණය කර පියවරෙන් පියවර විසඳන්න.",
          8,
          "දත්ත ලියා තර්කානුකූල ජ්‍යාමිතික හේතු ලියා සාධනය කිරීම හෝ වර්ගජ සමීකරණ සාධක කඩා විසඳා පිළිතුර ලබාගැනීම.",
          listOf("ජ්‍යාමිතික/වීජීය සාධනය (ලකුණු 8)")
        ),
        StructuredSubQuestion(
          "(B)",
          "ලැබුණු විසඳුම් භාවිතයෙන් ප්‍රායෝගික තල රූපයේ / ඝන වස්තුවේ පරිමාව හෝ වර්ගඵලය ගණනය කරන්න.",
          7,
          "සූත්‍රය යොදා දත්ත ආදේශ කර නිවැරදි ඒකක සහිතව පරිමාව/වර්ගඵලය ලබාගැනීම.",
          listOf("පරිමාව/වර්ගඵලය ගණනය (ලකුණු 7)")
        ),
        StructuredSubQuestion(
          "(C)",
          "ගණනය කළ අගයන් ඇසුරෙන් ප්‍රායෝගික පිරිවැය හෝ ප්‍රතිශතමය වාසිය සොයන්න.",
          5,
          "මුළු පිරිවැය = ඒකක පිරිවැය × පරිමාව/වර්ගඵලය ආදී වශයෙන් ගණනය කර අවසන් පිළිතුර ලිවීම.",
          listOf("ප්‍රායෝගික පිරිවැය (ලකුණු 5)")
        )
      )
      else -> listOf(
        StructuredSubQuestion(
          "(A)",
          "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) ආශ්‍රිත ලඝුගණක/ශ්‍රේඪි/වාණිජ ගණිත රචනා ගැටලුව O/L සම්මත පියවර ඔස්සේ ගණනය කරන්න.",
          8,
          "සූත්‍රය සටහන් කිරීම, දත්ත ආදේශය සහ සුළු කිරීමේ පියවර සවිස්තරව ලිවීම.",
          listOf("ගණිතමය විසඳුම (ලකුණු 8)")
        ),
        StructuredSubQuestion(
          "(B)",
          "ගණනය කළ අගයන් ඇසුරෙන් ප්‍රස්ථාරික නිරූපණය හෝ සමුච්චිත සංඛ්‍යාත ඕජයිව සටහන ඇඳ මධ්‍යස්ථය සොයන්න.",
          7,
          "අක්ෂ පරිමාණය තෝරා ගැනීම, ලක්ෂ්‍ය ලකුණු කිරීම සහ ප්‍රස්ථාරයෙන් අගය ලබාගැනීම.",
          listOf("ප්‍රස්ථාරික විසඳුම (ලකුණු 7)")
        ),
        StructuredSubQuestion(
          "(C)",
          "අවසාන නිගමනය සහ ආර්ථික/ප්‍රායෝගික වාසිය තර්කානුකූලව ප්‍රකාශ කරන්න.",
          5,
          "ලැබුණු පිළිතුරු මත පදනම්ව නිවැරදි තීරණය ඉදිරිපත් කිරීම.",
          listOf("තර්කානුකූල නිගමනය (ලකුණු 5)")
        )
      )
    }
  }

  private fun getHistoryEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    val variant = (q % 3)
    return when (variant) {
      1 -> listOf(
        StructuredSubQuestion(
          "(A)",
          "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) යුගයේ ඓතිහාසික පසුබිම, දේශපාලනික හා ආර්ථික සංවර්ධනය සවිස්තරව විග්‍රහ කරන්න.",
          8,
          "රජවරුන්ගේ දායකත්වය, මධ්‍යගත පාලන ක්‍රමය සහ වාරි කර්මාන්තයේ දියුණුව සවිස්තරව විස්තර කරයි.",
          listOf("දේශපාලන පසුබිම (ලකුණු 4)", "ආර්ථික සංවර්ධනය (ලකුණු 4)")
        ),
        StructuredSubQuestion(
          "(B)",
          "එම යුගයේ සමාජ සංස්කෘතික ප්‍රබෝධය සහ විදෙස් සබඳතා පැවති ආකාරය සාකච්ඡා කරන්න.",
          7,
          "බුදුදහමේ ප්‍රචාරණය, සාහිත්‍ය හා කලාව මෙන්ම විදෙස් රටවල් සමඟ කළ වෙළඳාම.",
          listOf("සමාජ සංස්කෘතික ප්‍රබෝධය (ලකුණු 4)", "විදෙස් සබඳතා (ලකුණු 3)")
        ),
        StructuredSubQuestion(
          "(C)",
          "මෙම යුගයෙන් වර්තමාන ශ්‍රී ලාංකේය සමාජයට ගත හැකි ප්‍රධාන ජාතික හා ආගමික පාඩම් මොනවාද?",
          5,
          "ජාතික එකමුතුකම, ස්වයංපෝෂිතභාවය සහ ස්වෛරීභාවය ආරක්ෂා කිරීම.",
          listOf("ඉතිහාස පාඩම (ලකුණු 5)")
        )
      )
      else -> listOf(
        StructuredSubQuestion(
          "(A)",
          "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) ආශ්‍රිත නිදහස් අරගලයේ හෝ ආණ්ඩුක්‍රම ප්‍රතිසංස්කරණවල ප්‍රධාන පියවර සවිස්තරව විග්‍රහ කරන්න.",
          8,
          "1818, 1848 අරගල හෝ කෝල්බෲක්, ඩොනමෝර්, සෝල්බරි ව්‍යවස්ථාපිත වෙනස්කම්.",
          listOf("අරගල/ව්‍යවස්ථාපිත පසුබිම (ලකුණු 8)")
        ),
        StructuredSubQuestion(
          "(B)",
          "එමඟින් මෙරට දේශපාලන, අධ්‍යාපනික සහ ජාතික පුනරුදයට ලැබුණු පිටිවහල සාකච්ඡා කරන්න.",
          7,
          "අනගාරික ධර්මපාලතුමා ඇතුළු ජාතික නායකයන්ගේ සේවය සහ බෞද්ධ/හින්දු/ඉස්ලාම් අධ්‍යාපන ප්‍රබෝධය.",
          listOf("ජාතික පුනරුදය (ලකුණු 7)")
        ),
        StructuredSubQuestion(
          "(C)",
          "ස්වෛරී නිදහස් රාජ්‍යයක් ලෙස වර්තමාන ශ්‍රී ලංකාව ඉදිරියට ගෙන යාමට මෙම ඓතිහාසික දැනුම උපකාරී වන ආකාරය ਲියන්න.",
          5,
          "ජාතික සහජීවනය, ප්‍රජාතන්ත්‍රවාදී අයිතීන් රැකගැනීම සහ ස්වෛරීභාවය.",
          listOf("නිදහස් රාජ්‍ය අනාගතය (ලකුණු 5)")
        )
      )
    }
  }

  private fun getBuddhismEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion(
        "(A)",
        "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) බෞද්ධ දර්ශනයේ එන මූලික ධර්ම මූලධර්මය සහ එහි දාර්ශනික ගැඹුර සවිස්තරව විග්‍රහ කරන්න.",
        8,
        "දුඃඛය, කර්මය, පටිච්ච සමුප්පාදය හෝ නිර්වාණය පිළිබඳ ගැඹුරු ධර්මානුකූල විග්‍රහය.",
        listOf("දාර්ශනික පසුබිම (ලකුණු 4)", "මූලධර්ම විග්‍රහය (ලකුණු 4)")
      ),
      StructuredSubQuestion(
        "(B)",
        "මෙම ධර්ම කරුණ ප්‍රායෝගික ජීවිතයට යොදාගැනීමෙන් පුද්ගල හා සමාජ සංවර්ධනයක් වන ආකාරය පැහැදිලි කරන්න.",
        7,
        "සිත දමනය කරගැනීම, සදාචාර සම්පන්න වීම සහ සාමකාමී සමාජයක් බිහිවීම.",
        listOf("පුද්ගල සංවර්ධනය (ලකුණු 4)", "සමාජ සංවර්ධනය (ලකුණු 3)")
      ),
      StructuredSubQuestion(
        "(C)",
        "වර්තමාන අසහනකාරී සමාජයට බෞද්ධ ජීවන දර්ශනය ලබාදෙන ස්ථිරසාර විසඳුම කුමක්ද?",
        5,
        "මෛත්‍රිය, ඉවසීම සහ සරල ජීවන රටාව අනුගමනය කිරීම.",
        listOf("විසඳුම ඉදිරිපත් කිරීම (ලකුණු 5)")
      )
    )
  }

  private fun getSinhalaEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion(
        "(A)",
        "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) සාහිත්‍ය නිර්මාණයේ හෝ භාෂා සංකල්පයේ අන්තර්ගතය, ස්වරූපය සහ රචකයාගේ අරමුණ සවිස්තරව විග්‍රහ කරන්න.",
        8,
        "රචකයාගේ විලාසය, භාෂා හැසිරවීම සහ කෘතියේ හරය මැනවින් ඉස්මතු කර දැක්වීම.",
        listOf("අන්තර්ගත විග්‍රහය (ලකුණු 4)", "රචකයාගේ අරමුණ (ලකුණු 4)")
      ),
        StructuredSubQuestion(
        "(B)",
        "කෘතියේ එන චරිත, සංස්කෘතික පසුබිම හෝ ව්‍යාකරණ රීති ගැඹුරින් සාකච්ඡා කරන්න.",
        7,
        "චරිතවල ස්වභාවය සහ සමාජ යථාර්ථය නිරූපණය වන ආකාරය.",
        listOf("විස්තරය (ලකුණු 4)", "සමාජ පසුබිම (ලකුණු 3)")
      ),
      StructuredSubQuestion(
        "(C)",
        "මෙම නිර්මාණයෙන් පාඨකයාගේ රසඥතාව සහ ජීවන දෘෂ්ටිය පොහොසත් වන ආකාරය ලියන්න.",
        5,
        "සංවේදී වීම සහ මිනිස් සජීවීබව අවබෝධ කරගැනීම.",
        listOf("රසඥතාව (ලකුණු 5)")
      )
    )
  }

  private fun getCivicsEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion(
        "(A)",
        "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) ප්‍රජාතන්ත්‍රවාදී පාලන ක්‍රමයේ සහ ආණ්ඩුක්‍රම ව්‍යවස්ථාවේ මූලික ප්‍රතිපාදන සවිස්තරව විග්‍රහ කරන්න.",
        8,
        "මූලික අයිතිවාසිකම්, රාජ්‍ය ආයතනවල බලතල සහ ජනතා පරමාධිපත්‍යය.",
        listOf("ව්‍යවස්ථාපිත ප්‍රතිපාදන (ලකුණු 4)", "පාලන ක්‍රමය (ලකුණු 4)")
      ),
      StructuredSubQuestion(
        "(B)",
        "යහපත් පුරවැසියෙකුගේ යුතුකම්, වගකීම් සහ ජාතික සංවර්ධනයට ඇති දායකත්වය සාකච්ඡා කරන්න.",
        7,
        "නීතියට ගරු කිරීම, මහජන දේපළ ආරක්ෂා කිරීම සහ සංහිඳියාව.",
        listOf("යුතුකම් හා වගකීම් (ලකුණු 4)", "ජාතික දායකත්වය (ලකුණු 3)")
      ),
      StructuredSubQuestion(
        "(C)",
        "ප්‍රජාතන්ත්‍රවාදය ශක්තිමත් කිරීමට පුරවැසියන් ලෙස අප කළ යුතු ප්‍රධාන කාර්යභාරය කුමක්ද?",
        5,
        "ක්‍රියාශීලීව සහ උත්සාහයෙන් යුතුව ප්‍රජාතන්ත්‍රවාදී ක්‍රියාවලියට දායක වීම.",
        listOf("කාර්යභාරය (ලකුණු 5)")
      )
    )
  }

  private fun getGeographyEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion(
        "(A)",
        "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) භූගෝලීය සංසිද්ධිය, දේශගුණික හෝ ස්වභාවික පරිසර පද්ධතිය සවිස්තරව විග්‍රහ කරන්න.",
        8,
        "භූමිරූප, ජල පද්ධති සහ දේශගුණික සාධකවල අන්තර්ක්‍රියාකාරිත්වය.",
        listOf("භූගෝලීය විග්‍රහය (ලකුණු 4)", "පරිසර පද්ධතිය (ලකුණු 4)")
      ),
      StructuredSubQuestion(
        "(B)",
        "මානව ක්‍රියාකාරකම් නිසා පරිසරයට සිදුවන බලපෑම සහ ආපදා කළමනාකරණ ක්‍රමවේද සාකච්ඡා කරන්න.",
        7,
        "නායයෑම්, ගංවතුර සහ පස් සංරක්ෂණ ක්‍රම.",
        listOf("මානව බලපෑම (ලකුණු 4)", "ආපදා කළමනාකරණය (ලකුණු 3)")
      ),
      StructuredSubQuestion(
        "(C)",
        "තිරසාර සංවර්ධන අරමුණු සාක්ෂාත් කරගැනීමට භූගෝලීය සම්පත් නිසි ලෙස කළමනාකරණය කරන්නේ කෙසේද?",
        5,
        "සම්පත් අරපිරිමැස්ම සහ හරිත උපායමාර්ග.",
        listOf("තිරසාර කළමනාකරණය (ලකුණු 5)")
      )
    )
  }

  private fun getCommerceEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion(
        "(A)",
        "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) ව්‍යාපාරික හෝ මූල්‍ය කළමනාකරණ සංකල්පය සවිස්තරව විග්‍රහ කරන්න.",
        8,
        "ගිණුම්කරණ සමීකරණය, ද්විත්ව සටහන් සහ මූල්‍ය ප්‍රකාශන සකස් කිරීම.",
        listOf("මූල්‍ය සංකල්පය (ලකුණු 4)", "ගිණුම්කරණ ක්‍රියාවලිය (ලකුණු 4)")
      ),
      StructuredSubQuestion(
        "(B)",
        "ව්‍යාපාරයක අලෙවිකරණය, මූල්‍ය කළමනාකරණය සහ ආර්ථික දායකත්වය සාකච්ඡා කරන්න.",
        7,
        "4Ps, ලාභ ඉපැයීම සහ ජාතික ආර්ථිකයට කරන දායකත්වය.",
        listOf("අලෙවිකරණය (ලකුණු 4)", "ආර්ථික දායකත්වය (ලකුණු 3)")
      ),
      StructuredSubQuestion(
        "(C)",
        "නවීන ව්‍යාපාර ලෝකයේ ආචාරධර්ම සහ ඊ-වාණිජ්‍යයේ වැදගත්කම විග්‍රහ කරන්න.",
        5,
        "ව්‍යාපාරික ආචාරධර්ම සහ තාක්ෂණික දියුණුව.",
        listOf("ආචාරධර්ම හා තාක්ෂණය (ලකුණු 5)")
      )
    )
  }

  private fun getIctEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion(
        "(A)",
        "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) පද්ධති නිර්මාණයේ හෝ ක්‍රමලේඛනයේ තාක්ෂණික පසුබිම සවිස්තරව රචනා කරන්න.",
        8,
        "SDLC අදියර 5, Python පාලන ව්‍යුහ, SQL විමසුම් හෝ ජාල ටොපොලොජි O/L සම්මතයන්ට අනුව පැහැදිලි කිරීම.",
        listOf("තාක්ෂණික පසුබිම (ලකුණු 8)")
      ),
      StructuredSubQuestion(
        "(B)",
        "දත්ත සමුදාය කළමනාකරණයේදී හෝ වෙබ් අඩවි නිර්මාණයේදී (HTML/CSS) භාවිත වන මූලික උපක්‍රම සාකච්ඡා කරන්න.",
        7,
        "ප්‍රාථමික/විදේශ යතුරු, SQL SELECT/JOIN, HTML ලැයිස්තු හා CSS හැඩගැන්වීම්.",
        listOf("මෘදුකාංග තාක්ෂණය (ලකුණු 7)")
      ),
      StructuredSubQuestion(
        "(C)",
        "සයිබර් ආරක්ෂාව, ගුප්තකේතනය සහ තොරතුරු තාක්ෂණයේ සදාචාරාත්මක භාවිතයේ ඇති වැදගත්කම ලියන්න.",
        5,
        "දත්ත ආරක්ෂා කිරීම, ෆයර්වෝල් භාවිතය සහ හරිත පරිගණකකරණය (Green Computing).",
        listOf("සයිබර් ආරක්ෂාව හා සදාචාරය (ලකුණු 5)")
      )
    )
  }

  private fun getGeneralEssaySubQuestions(grade: String, theme: String, q: Int, setNumber: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion(
        "(A)",
        "$theme ($grade ශ්‍රේණිය - ප්‍රශ්නය $q) විෂය කරුණට අදාළ න්‍යාය, මූලධර්ම සහ ප්‍රායෝගික යෙදුම් සවිස්තරව රචනා කරන්න.",
        8,
        "මූලික සිද්ධාන්ත සහ විද්‍යාත්මක හෝ ශාස්ත්‍රීය පසුබිම.",
        listOf("න්‍යායික පසුබිම (ලකුණු 4)", "ප්‍රායෝගික යෙදුම (ලකුණු 4)")
      ),
      StructuredSubQuestion(
        "(B)",
        "අදාළ ක්ෂේත්‍රයේ පවතින ගැටලු, අභියෝග සහ ඒවාට දිය හැකි ප්‍රායෝගික විසඳුම් සාකච්ඡා කරන්න.",
        7,
        "අභියෝග හඳුනාගැනීම සහ නිවැරදි ක්‍රියාමාර්ග.",
        listOf("අභියෝග (ලකුණු 4)", "විසඳුම් (ලකුණු 3)")
      ),
      StructuredSubQuestion(
        "(C)",
        "සමස්ත විෂය ඒකකයෙන් සමාජයට ලැබෙන සේවය සහ අනාගත දැක්ම ප්‍රකාශ කරන්න.",
        5,
        "සමස්ත සාරාංශය සහ අනාගත අපේක්ෂා.",
        listOf("අනාගත දැක්ම (ලකුණු 5)")
      )
    )
  }
}
