package com.example

object OlSubjectToolsIctData {
  val tools: List<OlSubjectToolItem> = listOf(computerTool("ict_001", 1, 1, "පරිගණක පද්ධතියක මූලික අංග", "Basic Computer System Components", "හඳුන්වාදීම", "💻", "ආදාන (Input), සකසන (Processing), ප්‍රතිදාන (Output) සහ ගබඩා (Storage) ඒකක.", "CPU = ALU + CU + Registers", "දත්ත ඇතුළත් කර තොරතුරු ලබාගැනීම.", "O/L විභාගයේදී CPU හි අභ්‍යන්තර කොටස් නිතර අසයි."),
    computerTool("ict_002", 2, 1, "ද්විමය සංඛ්‍යා පද්ධතිය (Binary System)", "Binary Number System", "දත්ත නිරූපණය", "🔢", "පරිගණකය ක්‍රියාත්මක වන 0 සහ 1 ඉලක්කම් මත පදනම් වූ සංඛ්‍යා ක්‍රමය.", "පාදය 2 (Base 2); බිටු (Bit) සහ බයිට් (Byte = 8 bits).", "පරිගණක දත්ත ගබඩා කිරීම හා සැකසීම.", "1 Byte = 8 Bits වේ."),
    computerTool("ict_003", 3, 1, "තාර්කික ද්වාර (Logic Gates)", "Boolean Logic Gates", "ඩිජිටල් ඉලෙක්ට්‍රොනික", "⚡", "AND, OR, NOT, NAND, NOR, XOR තාර්කික ද්වාර.", "AND (ගුණ කිරීම), OR (එකතු කිරීම), NOT (ප්‍රතිවර්තනය).", " පරිපථ සැලසුම් කිරීම සහ තීරණ ගැනීම.", "NOT ද්වාරය ඉන්වර්ටර් (Inverter) ලෙස හැඳින්වේ."),
    computerTool("ict_004", 4, 1, "ඇල්ගොරිතම සහ ප්‍රවාහ සටහන්", "Algorithms & Flowcharts", "ක්‍රමලේඛනය", "📊", "ගැටළුවක් විසඳීමට පියවරෙන් පියවර අනුපිළිවෙල සහ රූපමය නිරූපණය.", "ආරම්භය/අවසානය (ඕවලාකාර), ක්‍රියාවලිය (आयතකය), තීරණය (දියමන්ති).", "පරිගණක වැඩසටහන් ලිවීමට මඟ පෙන්වීම.", "ප්‍රවාහ සටහන්වල තීරණ ගැනීම සඳහා දියමන්ති සංකේතය යොදා ගනී."),
    computerTool("ict_005", 5, 1, "HTML මූලික වෙබ් පිටු සැලසුම්කරණය", "HTML Web Design Basics", "වෙබ් තාක්ෂණය", "🌐", "HyperText Markup Language භාවිතයෙන් වෙබ් පිටු නිර්මාණය.", "<html>, <head>, <title>, <body>, <h1> to <h6>, <p>, <a>.", "වෙබ් අඩවි සංවර්ධනය කිරීම.", "HTML යනු ක්‍රමලේඛන භාෂාවක් නොව මාර්කප් භාෂාවකි."),
    computerTool("ict_006", 6, 1, "දත්ත සමුදාය කළමනාකරණය (DBMS)", "Databases & Tables (DBMS)", "දත්ත සමුදාය", "🗄️", "වගු (Tables), ක්ෂේත්ර (Fields), වාර්තා (Records) සහ ප්‍රාථමික යතුරු (Primary Keys).", "ප්‍රාථමික යතුර (Primary Key) මගින් එක් එක් වාර්තාව අනන්‍යව හඳුනාගනී.", "විශාල දත්ත ගබඩා පාලනය.", "Primary Key එකකට හිස් (Null) අගයන් ගත නොහැක."),
    computerTool("ict_007", 7, 1, "ජාල ස්ථර හා ස්ථලෝපෝපාක (Network Topologies)", "Network Topologies", "පරිගණක ජාල", "🕸️", "Bus, Star, Ring, Mesh, Tree ජාල ස්ථලෝපෝපාක.", "Star ස්ථලෝපෝපාකයේ කේන්ද්‍රය ලෙස Switch/Hub ක්‍රියා කරයි.", "පරිගණක එකිනෙක සම්බන්ධ කිරීම.", "Star topology එකක එක් කේබලයක් බිඳ වැටුනද සෙසු පරිගණක ක්‍රියාත්මක වේ."),
    computerTool("ict_008", 8, 1, "පරිගණක ආරක්ෂාව සහ සයිබර් තර්ජන", "Cyber Security & Malware", "ආරක්ෂාව", "🔒", "වෛරස්, වර්ම්ස්, ට්‍රෝජන් හෝස්, ස්පයිවෙයාර් සහ ෆිෂිං ප්‍රහාර.", "యాන්ටිවයිරස් (Antivirus) මෘදුකාංග භාවිතය සහ ප්‍රබල මුදල්පද (Passwords).", "පෞද්ගලික දත්ත ආරක්ෂා කරගැනීම.", "ෆිෂිං (Phishing) යනු ව්‍යාජ ඊමේල් මගින් රහස් තොරතුරු සොරකම් කිරීමයි."),
    computerTool("ict_009", 9, 1, "පයිතන් මූලික ක්‍රමලේඛනය (Python Basics)", "Python Programming Basics", "ක්‍රමලේඛනය", "🐍", "විචල්‍ය (Variables), දත්ත වර්ග (Data types: int, float, str) සහ මුද්‍රණ විධානය (print).", "x = 10, name = \"Nimal\", print(x)", "ගණනය කිරීම් සහ ස්වයංක්‍රීයකරණය.", "පයිතන් හි විචල්‍ය නිර්වචනයට දත්ත වර්ග සඳහන් කිරීම අවශ්‍ය නොවේ."),
    computerTool("ict_010", 10, 1, "CSS මෝස්තර සහ විලාසිතා", "CSS Styling Basics", "වෙබ් සංවර්ධනය", "🎨", "Cascading Style Sheets මගින් වෙබ් පිටු අලංකාර කිරීම හා වර්ණ ගැන්වීම.", "color, background-color, font-size, margin, padding.", "වෙබ් පිටු ආකර්ෂණීය කිරීම.", "CSS මගින් HTML අන්තර්ගතය සහ පෙනුම වෙන් කරනු ලැබේ.")
  )

  private fun computerTool(id: String, num: Int, grp: Int, si: String, en: String, cat: String, ic: String, sum: String, form: String, prac: String, tip: String): OlSubjectToolItem {
    return OlSubjectToolItem(id, "තොරතුරු තාක්ෂණය", num, grp, si, en, cat, ic, sum, form, prac, tip)
  }
}
