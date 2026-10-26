package com.example

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy

/**
 * 🌟 100% ACCURATE GRADE 10 & 11 HISTORY MASTER HUB
 * Strictly adheres to the Sri Lankan G.C.E. O/L History Syllabus.
 * Designed with the exact same architecture as Sinhala Master Hub:
 * - Compact, low-height collapsible sub-sections (no text squishing or wrapping bugs)
 * - Full-screen dialog with BackHandler & system bars
 * - 2-Column Quick Overview Grid
 * - Inline module content preview with instant search
 * - Grade 10 & 11 toggles
 * - 20 Categories x 20 Facts (400 Facts Bank) + Special Syllabus Modules
 */
@Composable
fun HistoryGrade10And11MasterHub(
  currentGrade: String = "10",
  modifier: Modifier = Modifier
) {
  var activeGrade by remember { mutableStateOf(if (currentGrade.contains("11")) "11" else "10") }
  var selectedCategoryIndex by remember { mutableIntStateOf(-1) }
  var showFullScreenModal by remember { mutableStateOf(false) }

  val categories = remember { SubjectMasterHistory20x20.getCategories() }

  // Main Card Container with rich gradients to match Master Hub standard
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1B4B)), // Deep Indigo
    border = BorderStroke(1.2.dp, Color(0xFFD97706).copy(alpha = 0.6f)), // Amber / Gold
    modifier = modifier
      .fillMaxWidth()
      .testTag("history_grade_10_11_master_hub")
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      // Header Row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(Brush.linearGradient(listOf(Color(0xFFD97706), Color(0xFFB45309)))),
            contentAlignment = Alignment.Center
          ) {
            Text("🏛️", fontSize = 16.sp)
          }
          Spacer(modifier = Modifier.width(8.dp))
          Column {
            Text(
              text = "ඉතිහාසය විෂය නිර්දේශ Master Hub",
              color = Color.White,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = "රාජධානි • වාරි ශිෂ්ටාචාරය • මූලාශ්‍ර • කරුණු 400",
              color = Color(0xFFFDE68A),
              fontSize = 9.5.sp
            )
          }
        }

        Surface(
          color = Color(0xFFD97706),
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = "100% ආවරණය",
            color = Color.White,
            fontSize = 8.5.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Grade Toggle Pills: 10 ශ්‍රේණිය vs 11 ශ්‍රේණිය
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(10.dp))
          .background(Color(0xFF0F172A))
          .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        listOf("10", "11").forEach { g ->
          val isSelected = activeGrade == g
          Surface(
            onClick = { activeGrade = g },
            shape = RoundedCornerShape(8.dp),
            color = if (isSelected) Color(0xFFD97706) else Color.Transparent,
            modifier = Modifier.weight(1f)
          ) {
            Row(
              modifier = Modifier.padding(vertical = 5.dp),
              horizontalArrangement = Arrangement.Center,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = if (g == "10") "📘 10 ශ්‍රේණිය" else "📙 11 ශ්‍රේණිය",
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else Color(0xFF94A3B8)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Module Selector Strip (Compact Horizontal Grid)
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        itemsIndexed(categories) { index, cat ->
          val isSelected = selectedCategoryIndex == index
          Surface(
            onClick = {
              selectedCategoryIndex = if (isSelected) -1 else index
            },
            shape = RoundedCornerShape(8.dp),
            color = if (isSelected) cat.color else Color(0xFF1E293B),
            border = BorderStroke(1.dp, if (isSelected) Color.White else cat.color.copy(alpha = 0.4f))
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(cat.icon, fontSize = 11.sp)
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = cat.titleSinhala,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.widthIn(max = 130.dp)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Content Display Area depending on Selected Module
      if (selectedCategoryIndex == -1) {
        HistoryQuickOverviewGrid(
          categories = categories,
          onSelectModule = { selectedCategoryIndex = it }
        )
      } else {
        HistoryInlineModuleContent(
          activeGrade = activeGrade,
          categories = categories,
          selectedCategoryIndex = selectedCategoryIndex,
          onClose = { selectedCategoryIndex = -1 },
          onOpenFullscreen = { showFullScreenModal = true }
        )
      }
    }
  }

  // Full Screen Modal Dialog for deep study
  if (showFullScreenModal) {
    HistoryGrade10And11MasterHubDialog(
      initialGrade = activeGrade,
      initialCategoryIndex = selectedCategoryIndex,
      categories = categories,
      onDismiss = { showFullScreenModal = false }
    )
  }
}

@Composable
fun HistoryInlineModuleContent(
  activeGrade: String,
  categories: List<SubjectMasterCategory>,
  selectedCategoryIndex: Int,
  onClose: () -> Unit,
  onOpenFullscreen: () -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  val category = categories[selectedCategoryIndex]

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(10.dp))
      .background(Color(0xFF0F172A))
      .padding(8.dp)
  ) {
    // Header & Control Row
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f, fill = false)) {
        Text(category.icon, fontSize = 14.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = category.titleSinhala,
          fontSize = 11.5.sp,
          fontWeight = FontWeight.Bold,
          color = category.color,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }

      Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        IconButton(
          onClick = onOpenFullscreen,
          modifier = Modifier.size(26.dp)
        ) {
          Icon(Icons.Default.Fullscreen, contentDescription = "Fullscreen", tint = category.color, modifier = Modifier.size(18.dp))
        }
        IconButton(
          onClick = onClose,
          modifier = Modifier.size(26.dp)
        ) {
          Icon(Icons.Default.Close, contentDescription = "Close", tint = Color(0xFF94A3B8), modifier = Modifier.size(18.dp))
        }
      }
    }

    // Search bar
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      placeholder = { Text("කරුණු අතර සොයන්න...", fontSize = 9.5.sp, color = Color(0xFF94A3B8)) },
      singleLine = true,
      modifier = Modifier
        .fillMaxWidth()
        .height(44.dp)
        .padding(vertical = 2.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedBorderColor = category.color,
        unfocusedBorderColor = Color(0xFF334155),
        focusedContainerColor = Color(0xFF1E293B),
        unfocusedContainerColor = Color(0xFF1E293B)
      ),
      trailingIcon = {
        if (searchQuery.isNotEmpty()) {
          IconButton(onClick = { searchQuery = "" }) {
            Icon(Icons.Default.Close, contentDescription = "Clear", tint = Color.LightGray, modifier = Modifier.size(14.dp))
          }
        }
      }
    )

    Spacer(modifier = Modifier.height(6.dp))

    // Sub-section List (showing 3 compact items in inline view)
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      val items = category.points.filter {
        searchQuery.isBlank() || it.title.contains(searchQuery, true) || it.detail.contains(searchQuery, true)
      }.take(3)

      if (items.isEmpty()) {
        Text("ගැළපෙන කරුණු හමු නොවීය.", color = Color(0xFF94A3B8), fontSize = 10.sp)
      } else {
        items.forEach { point ->
          HistoryFactCompactCard(point, category.color)
        }
      }

      Button(
        onClick = onOpenFullscreen,
        colors = ButtonDefaults.buttonColors(containerColor = category.color),
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
        shape = RoundedCornerShape(8.dp)
      ) {
        Text("සියලු කරුණු 20 බලන්න (View All)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
      }
    }
  }
}

@Composable
fun HistoryFactCompactCard(point: SubjectFactPoint, themeColor: Color) {
  Surface(
    shape = RoundedCornerShape(8.dp),
    color = Color(0xFF1E293B),
    border = BorderStroke(0.8.dp, Color(0xFF334155)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier.padding(8.dp),
      verticalAlignment = Alignment.Top
    ) {
      Box(
        modifier = Modifier
          .size(20.dp)
          .background(themeColor.copy(alpha = 0.2f), CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Text(text = "${point.number}", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = themeColor)
      }
      Spacer(modifier = Modifier.width(8.dp))
      Column {
        Text(text = point.title, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Text(text = point.detail, color = Color(0xFF94A3B8), fontSize = 9.5.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
      }
    }
  }
}

/**
 * 🌟 FULL SCREEN DEDICATED DIALOG FOR HISTORY 10 & 11 SYLLABUS MASTER HUB
 * Strictly mirrors SinhalaGrade10And11MasterHubDialog layout and aesthetics.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryGrade10And11MasterHubDialog(
  initialGrade: String,
  initialCategoryIndex: Int = -1,
  initialModuleIndex: Int = 0,
  categories: List<SubjectMasterCategory>,
  onDismiss: () -> Unit
) {
  var activeGrade by remember { mutableStateOf(initialGrade) }
  var selectedCategoryIndex by remember { mutableIntStateOf(initialCategoryIndex) }
  var hubMode by remember { mutableIntStateOf(if (initialCategoryIndex != -1) 0 else 1) } // 0 = 20 Categories (Fact Bank), 1 = Specialized Modules
  var selectedModuleTab by remember { mutableIntStateOf(initialModuleIndex) }
  var searchQuery by remember { mutableStateOf("") }
  var isSearchExpanded by remember { mutableStateOf(false) }

  BackHandler {
    if (selectedCategoryIndex != -1) {
      selectedCategoryIndex = -1
    } else {
      onDismiss()
    }
  }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(
      usePlatformDefaultWidth = false,
      decorFitsSystemWindows = false,
      securePolicy = SecureFlagPolicy.SecureOff
    )
  ) {
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .testTag("history_10_11_fullscreen_dialog"),
      color = Color(0xFF0F172A) // Deep background
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // ULTRA-COMPACT TOP APP BAR (Single slim row, ~44dp height)
        Surface(
          color = Color(0xFF1E1B4B),
          tonalElevation = 2.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .statusBarsPadding()
              .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.weight(1f)
            ) {
              IconButton(
                onClick = {
                  if (selectedCategoryIndex != -1) selectedCategoryIndex = -1 else onDismiss()
                },
                modifier = Modifier.size(36.dp)
              ) {
                Icon(
                  imageVector = if (selectedCategoryIndex == -1) Icons.Default.Close else Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = "Back",
                  tint = Color.White
                )
              }
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = if (selectedCategoryIndex == -1) "ඉතිහාසය විෂය නිර්දේශ Hub" else categories[selectedCategoryIndex].titleSinhala,
                color = Color.White,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
              )
            }

            // Quick Grade Switcher Pill in Top Bar
            Surface(
              onClick = { activeGrade = if (activeGrade == "10") "11" else "10" },
              shape = RoundedCornerShape(12.dp),
              color = Color(0xFFD97706),
              modifier = Modifier.padding(horizontal = 4.dp)
            ) {
              Text(
                text = if (activeGrade == "10") "📘 10 වසර" else "📙 11 වසර",
                color = Color.White,
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.5.dp)
              )
            }

            IconButton(
              onClick = { isSearchExpanded = !isSearchExpanded },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = if (isSearchExpanded) Icons.Default.Close else Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFFFDE68A)
              )
            }
          }
        }

        // Expandable Slim Search Bar
        AnimatedVisibility(visible = isSearchExpanded) {
          Surface(
            color = Color(0xFF1E1B4B),
            modifier = Modifier.fillMaxWidth()
          ) {
            OutlinedTextField(
              value = searchQuery,
              onValueChange = { searchQuery = it },
              placeholder = { Text("රජුගේ නම, රාජධානිය, වැව, අරගලය සොයන්න...", fontSize = 11.sp, color = Color(0xFF94A3B8)) },
              leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFFFDE68A), modifier = Modifier.size(18.dp)) },
              trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                  IconButton(onClick = { searchQuery = "" }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear", tint = Color.White, modifier = Modifier.size(16.dp))
                  }
                }
              },
              singleLine = true,
              colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFFD97706),
                unfocusedBorderColor = Color(0xFF334155),
                focusedContainerColor = Color(0xFF0F172A),
                unfocusedContainerColor = Color(0xFF0F172A)
              ),
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 4.dp)
                .height(48.dp)
            )
          }
        }

        // CONTENT SECTION - FULL HEIGHT SCROLLABLE
        Box(modifier = Modifier.fillMaxSize()) {
          if (selectedCategoryIndex != -1) {
            // Category Detail View: Full-screen scrollable 20 facts
            SubjectMaster20CategoriesDetailView(
              subjectName = "ඉතිහාසය",
              categories = categories,
              selectedCategoryNumber = categories[selectedCategoryIndex].categoryNumber,
              onSelectCategory = { num ->
                if (num == 0) selectedCategoryIndex = -1
                else {
                  val newIdx = categories.indexOfFirst { it.categoryNumber == num }
                  if (newIdx != -1) selectedCategoryIndex = newIdx
                }
              },
              modifier = Modifier.fillMaxSize()
            )
          } else {
            // Overview Mode: Entire screen scrolls together (Mode Selector + Content)
            LazyColumn(
              modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
              contentPadding = PaddingValues(top = 8.dp, bottom = 28.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              // 1. Scrollable Mode Selector (400 Facts vs Advanced Modules)
              item(key = "mode_selector") {
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF0F172A))
                    .padding(2.dp),
                  horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  Surface(
                    onClick = { hubMode = 0 },
                    shape = RoundedCornerShape(6.dp),
                    color = if (hubMode == 0) Color(0xFFD97706) else Color.Transparent,
                    modifier = Modifier.weight(1f)
                  ) {
                    Text(
                      text = "📚 කරුණු 400 බැංකුව (කාණ්ඩ 20)",
                      fontSize = 10.5.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (hubMode == 0) Color.White else Color(0xFFCBD5E1),
                      textAlign = TextAlign.Center,
                      modifier = Modifier.padding(vertical = 6.dp)
                    )
                  }
                  Surface(
                    onClick = { hubMode = 1 },
                    shape = RoundedCornerShape(6.dp),
                    color = if (hubMode == 1) Color(0xFF4F46E5) else Color.Transparent,
                    modifier = Modifier.weight(1f)
                  ) {
                    Text(
                      text = "🏛️ විෂය ඒකක (Advanced Modules)",
                      fontSize = 10.5.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (hubMode == 1) Color.White else Color(0xFFCBD5E1),
                      textAlign = TextAlign.Center,
                      modifier = Modifier.padding(vertical = 6.dp)
                    )
                  }
                }
              }

              if (hubMode == 0) {
                // 20 Categories Quick Overview Grid
                item(key = "overview_grid") {
                  HistoryQuickOverviewGrid(
                    categories = categories,
                    onSelectModule = { selectedCategoryIndex = it }
                  )
                }
              } else {
                // Specialized Modules Tabs & Content
                item(key = "module_tabs") {
                  ScrollableTabRow(
                    selectedTabIndex = selectedModuleTab,
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFFDE68A),
                    edgePadding = 0.dp,
                    indicator = { tabPositions ->
                      TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedModuleTab]),
                        color = Color(0xFFD97706),
                        height = 2.5.dp
                      )
                    },
                    divider = { HorizontalDivider(color = Color(0xFF334155), thickness = 0.6.dp) }
                  ) {
                    val modules = HistoryModuleType.values()
                    modules.forEachIndexed { index, module ->
                      Tab(
                        selected = selectedModuleTab == index,
                        onClick = { selectedModuleTab = index },
                        text = {
                          Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(module.icon, fontSize = 11.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                              text = module.title,
                              fontSize = 10.5.sp,
                              fontWeight = if (selectedModuleTab == index) FontWeight.Bold else FontWeight.Normal,
                              color = if (selectedModuleTab == index) Color(0xFFFDE68A) else Color(0xFF94A3B8)
                            )
                          }
                        }
                      )
                    }
                  }
                }

                val currentModule = HistoryModuleType.values()[selectedModuleTab]
                when (currentModule) {
                  HistoryModuleType.KINGDOMS_RULERS -> {
                    val items = HistoryAdvancedSyllabusRepository.rulersList.filter {
                      it.grade == activeGrade && (searchQuery.isBlank() || it.rulerName.contains(searchQuery, true) || it.kingdom.contains(searchQuery, true) || it.epithet.contains(searchQuery, true))
                    }
                    if (items.isEmpty()) {
                      item { EmptySearchResultNotice() }
                    } else {
                      items(items, key = { it.id }) { ruler ->
                        HistoryRulerCard(ruler)
                      }
                    }
                  }

                  HistoryModuleType.IRRIGATION_CIVILIZATION -> {
                    val items = HistoryAdvancedSyllabusRepository.irrigationList.filter {
                      it.grade == activeGrade && (searchQuery.isBlank() || it.name.contains(searchQuery, true) || it.builtBy.contains(searchQuery, true) || it.technologyComponent.contains(searchQuery, true))
                    }
                    if (items.isEmpty()) {
                      item { EmptySearchResultNotice() }
                    } else {
                      items(items, key = { it.id }) { irrigation ->
                        HistoryIrrigationCard(irrigation)
                      }
                    }
                  }

                  HistoryModuleType.HISTORICAL_SOURCES -> {
                    val items = HistoryAdvancedSyllabusRepository.sourcesList.filter {
                      it.grade == activeGrade && (searchQuery.isBlank() || it.sourceName.contains(searchQuery, true) || it.category.contains(searchQuery, true) || it.authorOrOrigin.contains(searchQuery, true))
                    }
                    if (items.isEmpty()) {
                      item { EmptySearchResultNotice() }
                    } else {
                      items(items, key = { it.id }) { source ->
                        HistorySourceCard(source)
                      }
                    }
                  }

                  HistoryModuleType.FOREIGN_RULE_STRUGGLES -> {
                  val items = HistoryAdvancedSyllabusRepository.colonialUprisingsList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.title.contains(searchQuery, true) || it.leaders.any { l -> l.contains(searchQuery, true) })
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    items(items, key = { it.id }) { uprising ->
                      HistoryUprisingCard(uprising)
                    }
                  }
                }

                HistoryModuleType.CONSTITUTIONS -> {
                  val items = HistoryAdvancedSyllabusRepository.constitutionsList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.name.contains(searchQuery, true) || it.year.contains(searchQuery) || it.governorOrCommission.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    items(items, key = { it.id }) { constitution ->
                      HistoryConstitutionCard(constitution)
                    }
                  }
                }

                HistoryModuleType.MAP_LOCATIONS -> {
                  val items = HistoryAdvancedSyllabusRepository.mapLocationsList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.locationName.contains(searchQuery, true) || it.provinceOrDistrict.contains(searchQuery, true) || it.category.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    items(items, key = { it.id }) { location ->
                      HistoryMapLocationCard(location)
                    }
                  }
                }

                HistoryModuleType.WORLD_HISTORY -> {
                  val items = HistoryAdvancedSyllabusRepository.worldHistoryList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.eventTitle.contains(searchQuery, true) || it.region.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    items(items, key = { it.id }) { worldEvent ->
                      HistoryWorldEventCard(worldEvent)
                    }
                  }
                }

                HistoryModuleType.ART_SCULPTURE -> {
                  val items = HistoryExpandedRepository.artSculpturesList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.title.contains(searchQuery, true) || it.category.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    item { HistoryArtSculptureSection(items) }
                  }
                }

                HistoryModuleType.HISTORICAL_TREATIES -> {
                  val items = HistoryExpandedRepository.treatiesList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.treatyName.contains(searchQuery, true) || it.signatoryParties.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    item { HistoryTreatiesSection(items) }
                  }
                }

                HistoryModuleType.REVOLUTIONS_WORLD_WARS -> {
                  val items = HistoryExpandedRepository.worldErasList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.eraTitle.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    item { HistoryWorldErasSection(items) }
                  }
                }

                HistoryModuleType.EXAM_SECRETS -> {
                  val items = HistoryAdvancedSyllabusRepository.examTipsList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.questionTopic.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    items(items, key = { it.id }) { tip ->
                      HistoryExamTipCard(tip)
                    }
                  }
                }

                HistoryModuleType.RAPID_MCQS -> {
                  val items = HistoryAdvancedSyllabusRepository.rapidMcqsList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.question.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    items(items, key = { it.id }) { mcq ->
                      HistoryRapidMcqCard(mcq)
                    }
                  }
                }

                HistoryModuleType.CASCADE_TANKS -> {
                  val items = HistoryExpandedRepository.cascadeTanksList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.componentName.contains(searchQuery, true) || it.mainFunction.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    item { HistoryCascadeTanksSection(items) }
                  }
                }

                HistoryModuleType.NATIONAL_REVIVAL -> {
                  val items = HistoryExpandedRepository.revivalList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.leaderOrMovement.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    item { HistoryRevivalSection(items) }
                  }
                }

                HistoryModuleType.TIMELINE_CHALLENGE -> {
                  val items = HistoryExpandedRepository.timelineChallengesList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.challengeTitle.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    item { HistoryTimelineChallengeSection(items) }
                  }
                }

                HistoryModuleType.ARTIFACT_IDENTIFIER -> {
                  val items = HistoryExpandedRepository.artifactsList.filter {
                    it.grade == activeGrade && (searchQuery.isBlank() || it.artifactName.contains(searchQuery, true) || it.location.contains(searchQuery, true))
                  }
                  if (items.isEmpty()) {
                    item { EmptySearchResultNotice() }
                  } else {
                    item { HistoryArtifactSection(items) }
                  }
                }

                HistoryModuleType.ESSAY_GUIDE -> {
                  item { HistoryEssayGuideSubSection(grade = activeGrade) }
                }

                HistoryModuleType.MAP_QUIZ -> {
                  item { HistoryMapQuizSection(onClose = { selectedModuleTab = 0 }) }
                }

                HistoryModuleType.TIMELINE -> {
                  item { HistoryTimelineSection(onClose = { selectedModuleTab = 0 }) }
                }

                HistoryModuleType.VISUAL_GALLERY -> {
                  item { HistoryVisualGallerySection(onClose = { selectedModuleTab = 0 }) }
                }

                HistoryModuleType.COMPARISON -> {
                  item { HistoryComparisonSection(onClose = { selectedModuleTab = 0 }) }
                }

                HistoryModuleType.ESSAY_TEMPLATES -> {
                  item { HistoryEssayTemplatesSection(onClose = { selectedModuleTab = 0 }) }
                }

                else -> {
                  item { Text("තවම සම්පූර්ණ කර නොමැත.", color = Color.Gray, modifier = Modifier.padding(16.dp)) }
                }
              }
            }
          }
        }
      }
    }
  }
}
}

/**
 * Backward compatibility wrapper for HistoryFullScreenModal
 */
@Composable
fun HistoryFullScreenModal(
  initialGrade: String,
  initialTab: Int = 0,
  onDismiss: () -> Unit
) {
  HistoryGrade10And11MasterHubDialog(
    initialGrade = initialGrade,
    initialCategoryIndex = -1,
    initialModuleIndex = initialTab,
    categories = SubjectMasterHistory20x20.getCategories(),
    onDismiss = onDismiss
  )
}

@Composable
fun HistoryQuickOverviewGrid(
  categories: List<SubjectMasterCategory>,
  onSelectModule: (Int) -> Unit
) {
  Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
    // 2-column layout to match Sinhala Master Hub
    for (i in categories.indices step 2) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        HistoryFeatureButton(
          category = categories[i],
          modifier = Modifier.weight(1f),
          onClick = { onSelectModule(i) }
        )
        if (i + 1 < categories.size) {
          HistoryFeatureButton(
            category = categories[i + 1],
            modifier = Modifier.weight(1f),
            onClick = { onSelectModule(i + 1) }
          )
        } else {
          HistoryFeatureButton(
            category = null,
            customTitle = "AI උපදේශක",
            customIcon = "🤖",
            customSubtitle = "ඉතිහාසය AI සහාය",
            customColor = Color(0xFFDB2777),
            modifier = Modifier.weight(1f),
            onClick = { }
          )
        }
      }
    }
  }
}

@Composable
fun HistoryFeatureButton(
  category: SubjectMasterCategory?,
  customTitle: String = "",
  customIcon: String = "",
  customSubtitle: String = "",
  customColor: Color = Color.Gray,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  val title = category?.titleSinhala ?: customTitle
  val icon = category?.icon ?: customIcon
  val color = category?.color ?: customColor
  val subtitle = category?.summary ?: customSubtitle

  Surface(
    onClick = onClick,
    shape = RoundedCornerShape(8.dp),
    color = color.copy(alpha = 0.25f),
    border = BorderStroke(1.dp, color.copy(alpha = 0.6f)),
    modifier = modifier
  ) {
    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(icon, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = title,
          fontSize = 10.5.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }
      Text(
        text = subtitle,
        fontSize = 8.5.sp,
        color = Color(0xFFFDE68A),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

// -------------------------------------------------------------
// COMPACT COLLAPSIBLE CARD COMPONENTS (SINHALA DESIGN SYSTEM)
// Minimal default height (~40dp), non-squishable category pills,
// smooth expand/collapse animations, and clean bullet structures.
// -------------------------------------------------------------

@Composable
fun HistoryRulerCard(ruler: HistoryRulerItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF334155)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      // Compact Header with minimal height (~40dp) - Badge first, non-squishable!
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFFD97706).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = ruler.kingdom,
              color = Color(0xFFFBBF24),
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = ruler.rulerName,
            color = Color.White,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = if (expanded) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF94A3B8),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        // Quick summary line when collapsed (matching Sinhala format)
        Text(
          text = "${ruler.epithet} • කාලය: ${ruler.period}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        // Expanded Details
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        // Ruler Epithet & Period Banner
        Surface(
          color = Color(0xFF0F172A),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "👑 ${ruler.epithet}", color = Color(0xFFFDE68A), fontSize = 11.5.sp, fontWeight = FontWeight.Bold)
            Text(text = "කාලය: ${ruler.period}", color = Color(0xFF94A3B8), fontSize = 10.5.sp)
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Political services
        Text("👑 දේශපාලන මෙහෙවර:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF67E8F9))
        ruler.politicalServices.forEach { s ->
          Row(modifier = Modifier.padding(vertical = 1.5.dp)) {
            Text("•", color = Color(0xFF38BDF8), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Text(s, fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Economic services
        Text("🌾 ආර්ථික & වාරි සේවාවන්:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF86EFAC))
        ruler.economicServices.forEach { s ->
          Row(modifier = Modifier.padding(vertical = 1.5.dp)) {
            Text("•", color = Color(0xFF4ADE80), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Text(s, fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Religious/Cultural services
        Text("🛕 ශාසනික & සංස්කෘතික සේවාවන්:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
        ruler.religiousCulturalServices.forEach { s ->
          Row(modifier = Modifier.padding(vertical = 1.5.dp)) {
            Text("•", color = Color(0xFFF87171), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Text(s, fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)
          }
        }

        if (ruler.keyExamFacts.isNotEmpty()) {
          Spacer(modifier = Modifier.height(6.dp))
          Surface(
            color = Color(0xFF451A03),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("💡 O/L Tip: ", color = Color(0xFFFDE047), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
              Text(ruler.keyExamFacts.joinToString(" • "), color = Color(0xFFFEF08A), fontSize = 10.5.sp)
            }
          }
        }
      }
    }
  }
}

@Composable
fun HistoryIrrigationCard(item: HistoryIrrigationItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF0284C7).copy(alpha = 0.4f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFF0284C7).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = item.technologyComponent,
              color = Color(0xFF38BDF8),
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.name,
            color = Color(0xFFBAE6FD),
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF94A3B8),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "නිර්මාතෘ / යුගය: ${item.builtBy}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("නිර්මාතෘ / යුගය: ${item.builtBy}", fontSize = 11.sp, color = Color(0xFF38BDF8), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "ස්වභාවය සහ ලක්ෂණ: ${item.features}",
          fontSize = 11.sp,
          color = Color(0xFFE2E8F0),
          lineHeight = 16.sp
        )

        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF082F49),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("තාක්ෂණික වැදගත්කම:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF7DD3FC))
            Text(
              text = item.syllabusImportance,
              fontSize = 10.5.sp,
              color = Color(0xFFE0F2FE),
              lineHeight = 15.sp
            )
          }
        }
      }
    }
  }
}

@Composable
fun HistorySourceCard(source: HistorySourceItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF059669).copy(alpha = 0.4f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFF059669).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = source.category,
              color = Color(0xFF34D399),
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = source.sourceName,
            color = Color(0xFFA7F3D0),
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF94A3B8),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "කර්තෘ / සම්භවය: ${source.authorOrOrigin}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("කර්තෘ / සම්භවය: ${source.authorOrOrigin}", fontSize = 11.sp, color = Color(0xFF6EE7B7), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("අන්තර්ගතය: ${source.keyContents}", fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)

        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF064E3B).copy(alpha = 0.4f),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("ඓතිහාසික වටිනාකම:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6EE7B7))
            Text(source.historicalValue, fontSize = 10.5.sp, color = Color(0xFFD1FAE5), lineHeight = 15.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text("💡 විභාග ආදර්ශ ප්‍රශ්නය:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
            Text(source.examSampleQuestion, fontSize = 10.5.sp, color = Color.White, lineHeight = 15.sp)
          }
        }
      }
    }
  }
}

@Composable
fun HistoryUprisingCard(item: HistoryColonialUprisingItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFDC2626).copy(alpha = 0.4f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFFDC2626).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = item.periodOrYear,
              color = Color(0xFFFCA5A5),
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.title,
            color = Color(0xFFFCA5A5),
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFFFCA5A5),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "නායකයින්: ${item.leaders.joinToString(", ")}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "නායකයින්: ${item.leaders.joinToString(", ")}",
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFFFDE68A)
        )

        Spacer(modifier = Modifier.height(6.dp))
        Text("🔥 මූලික හේතු:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF87171))
        item.rootCauses.forEach { c ->
          Text(" • $c", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text("⚔️ ප්‍රධාන සිදුවීම්:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
        item.mainEvents.forEach { e ->
          Text(" • $e", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text("💥 ප්‍රතිඵල සහ බලපෑම:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF93C5FD))
        item.consequencesAndImpact.forEach { ci ->
          Text(" • $ci", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
        }
      }
    }
  }
}

@Composable
fun HistoryConstitutionCard(item: HistoryConstitutionItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF7C3AED).copy(alpha = 0.4f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFF7C3AED).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = item.year,
              color = Color(0xFFC4B5FD),
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.name,
            color = Color(0xFFDDD6FE),
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFFC4B5FD),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "කොමිසම: ${item.governorOrCommission} • ඡන්ද අයිතිය: ${item.votingRights}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("සභා ව්‍යුහය: ${item.councilStructure}", fontSize = 10.5.sp, color = Color(0xFFE2E8F0))
        Text("ඡන්ද අයිතිය: ${item.votingRights}", fontSize = 10.5.sp, color = Color(0xFFFDE68A), fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(6.dp))
        Text("✅ ප්‍රධාන ප්‍රතිසංස්කරණ:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFA7F3D0))
        item.keyReforms.forEach { r ->
          Text(" • $r", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text("⚠️ දුර්වලතා සහ සීමාවන්:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
        item.shortcomingsOrDrawbacks.forEach { d ->
          Text(" • $d", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
        }
      }
    }
  }
}

@Composable
fun HistoryMapLocationCard(item: HistoryMapLocationItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF2563EB).copy(alpha = 0.4f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFF1E3A8A),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = item.category,
              color = Color(0xFFBFDBFE),
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.locationName,
            color = Color(0xFF93C5FD),
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF93C5FD),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "පිහිටීම: ${item.provinceOrDistrict}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("පිහිටීම: ${item.provinceOrDistrict}", fontSize = 10.5.sp, color = Color(0xFF94A3B8))
        Spacer(modifier = Modifier.height(4.dp))
        Text("ඓතිහාසික වැදගත්කම: ${item.historicalSignificance}", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)

        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF172554),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text("🗺️", fontSize = 11.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "සිතියමේ ලකුණු කරන අයුරු: ${item.mapMarkingGuide}",
              fontSize = 10.sp,
              color = Color(0xFFBAE6FD),
              lineHeight = 14.sp
            )
          }
        }
      }
    }
  }
}

@Composable
fun HistoryWorldEventCard(item: HistoryWorldEventItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF0D9488).copy(alpha = 0.4f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFF0D9488).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = item.era,
              color = Color(0xFF5EEAD4),
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.eventTitle,
            color = Color(0xFF5EEAD4),
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF5EEAD4),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "කලාපය: ${item.region} • නායකයන්: ${item.pioneersOrLeaders.joinToString(", ")}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("ප්‍රමුඛ නායකයන් / පුරෝගාමීන්: ${item.pioneersOrLeaders.joinToString(", ")}", fontSize = 10.5.sp, color = Color(0xFFFDE68A))

        Spacer(modifier = Modifier.height(4.dp))
        Text("මූලික හේතු:", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2DD4BF))
        item.keyCauses.forEach { c ->
          Text(" • $c", fontSize = 10.sp, color = Color(0xFFE2E8F0))
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text("නව නිපැයුම් / ප්‍රතිඵල:", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF93C5FD))
        item.keyInnovationsOrConsequences.forEach { i ->
          Text(" • $i", fontSize = 10.sp, color = Color(0xFFE2E8F0))
        }

        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF134E4A),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "ලංකාවට ඇතිවූ බලපෑම: ${item.impactOnSriLanka}",
            fontSize = 10.sp,
            color = Color(0xFFCCFBF1),
            modifier = Modifier.padding(6.dp)
          )
        }
      }
    }
  }
}

@Composable
fun HistoryExamTipCard(item: HistoryExamEssayTip) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFE11D48).copy(alpha = 0.4f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFF881337),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = item.partMarkAllocations,
              color = Color.White,
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.questionTopic,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFDA4AF),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFFFDA4AF),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "රචනා ප්‍රශ්නයට Full Marks පිළිතුරු සැලසුම",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("📝 රචනා ප්‍රශ්නයට පිළිතුරු සැලසුම (Full Marks Structure):", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
        item.structuredParagraphPlan.forEachIndexed { idx, plan ->
          Text(" ${idx + 1}. $plan", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF4C0519),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("⚠️ සිසුන් නිතර කරන වැරදි:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
            Text(item.commonMistakes, fontSize = 10.sp, color = Color(0xFFFFF1F2), lineHeight = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text("🧠 මතක තබාගැනීමේ කෙටි ක්‍රමය (Mnemonic):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
            Text(item.mnemonicOrMemoryTrick, fontSize = 10.5.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
          }
        }
      }
    }
  }
}

@Composable
fun HistoryRapidMcqCard(item: HistoryRapidMcqItem) {
  var selectedIndex by remember { mutableStateOf<Int?>(null) }
  val isAnswered = selectedIndex != null

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF4F46E5).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          color = Color(0xFF4F46E5).copy(alpha = 0.25f),
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = "⚡ MCQ අභ්‍යාසය",
            color = Color(0xFFA5B4FC),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
        if (isAnswered) {
          val isCorrect = selectedIndex == item.correctIndex
          Text(
            text = if (isCorrect) "✓ නිවැරදියි!" else "✗ වැරදියි",
            color = if (isCorrect) Color(0xFF4ADE80) else Color(0xFFF87171),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = item.question,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        lineHeight = 17.sp
      )

      Spacer(modifier = Modifier.height(8.dp))

      item.options.forEachIndexed { index, option ->
        val isChosen = selectedIndex == index
        val isCorrect = index == item.correctIndex
        val bgColor = when {
          !isAnswered -> Color(0xFF0F172A)
          isCorrect -> Color(0xFF065F46)
          isChosen -> Color(0xFF991B1B)
          else -> Color(0xFF0F172A)
        }
        val borderColor = when {
          !isAnswered -> Color(0xFF334155)
          isCorrect -> Color(0xFF10B981)
          isChosen -> Color(0xFFEF4444)
          else -> Color(0xFF334155)
        }

        Surface(
          color = bgColor,
          shape = RoundedCornerShape(6.dp),
          border = BorderStroke(0.8.dp, borderColor),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.5.dp)
            .clickable(enabled = !isAnswered) {
              selectedIndex = index
            }
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "(${index + 1})",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = if (isChosen || isCorrect) Color.White else Color(0xFF94A3B8)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = option,
              fontSize = 11.sp,
              color = Color.White,
              modifier = Modifier.weight(1f)
            )
            if (isAnswered) {
              if (isCorrect) {
                Text("✓ නිවැරදියි", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6EE7B7))
              } else if (isChosen) {
                Text("✗ වැරදියි", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
              }
            }
          }
        }
      }

      if (isAnswered) {
        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF312E81),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.Top
          ) {
            Text("💡", fontSize = 11.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = item.explanation,
              fontSize = 10.5.sp,
              color = Color(0xFFE0E7FF),
              lineHeight = 15.sp
            )
          }
        }
      }
    }
  }
}

@Composable
fun EmptySearchResultNotice() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 32.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text("🔍", fontSize = 28.sp)
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = "සොයන ලද විෂය කොටස හමු නොවීය.\nකරුණාකර වෙනත් නමක් හෝ අංකයක් යොදන්න.",
        color = Color(0xFF94A3B8),
        fontSize = 12.sp,
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
fun HistoryEssayGuideSubSection(grade: String) {
  val essays = remember(grade) {
    ComprehensiveStructuredEssayRepository.getQuestions(
      grade = grade,
      subject = "ඉතිහාසය"
    ).filter { it.type == "ESSAY" }
  }

  Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Text("රචනා ලිවීමේ සැකිලි සහ ආදර්ශ පිළිතුරු", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
    Spacer(modifier = Modifier.height(8.dp))
    essays.forEach { essay ->
      Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color(0xFF334155))
      ) {
        Column(modifier = Modifier.padding(10.dp)) {
          Text(essay.topicSinhala, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A), fontSize = 12.5.sp)
          Spacer(modifier = Modifier.height(4.dp))
          Text(essay.mainScenario, fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)
        }
      }
    }
  }
}

@Composable
private fun HistoryMapQuizSection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("🗺️ සිතියම් පුහුණුව", color = Color.White, fontWeight = FontWeight.Bold)
    Text("විභාගයට අනිවාර්යයෙන්ම ලැබෙන සිතියම් ලකුණු 10ක කොටස සඳහා පුහුණුව මෙහි ඇතුළත් වේ.", color = Color(0xFF94A3B8), fontSize = 11.sp)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706))) {
      Text("වසන්න (Close)", fontSize = 11.sp)
    }
  }
}

@Composable
private fun HistoryTimelineSection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("⏳ කාලරේඛාව", color = Color.White, fontWeight = FontWeight.Bold)
    Text("අනුරාධපුර, පොළොන්නරු, දඹදෙණිය සිට මහනුවර දක්වා ඓතිහාසික සිදුවීම් කාලානුක්‍රමය.", color = Color(0xFF94A3B8), fontSize = 11.sp)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706))) {
      Text("වසන්න (Close)", fontSize = 11.sp)
    }
  }
}

@Composable
private fun HistoryVisualGallerySection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("🖼️ පුරාවස්තු ගැලරිය", color = Color.White, fontWeight = FontWeight.Bold)
    Text("සඳකඩපහණ, මුරගල්, කොරවක්ගල් සහ ලෝහ ප්‍රතිමා විවරණය.", color = Color(0xFF94A3B8), fontSize = 11.sp)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706))) {
      Text("වසන්න (Close)", fontSize = 11.sp)
    }
  }
}

@Composable
private fun HistoryComparisonSection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("⚖️ සංසන්දන වගු", color = Color.White, fontWeight = FontWeight.Bold)
    Text("1818 සහ 1848 නිදහස් අරගල, ඩොනමෝර් සහ සෝල්බරි ආණ්ඩුක්‍රම සංසන්දනය.", color = Color(0xFF94A3B8), fontSize = 11.sp)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706))) {
      Text("වසන්න (Close)", fontSize = 11.sp)
    }
  }
}

@Composable
private fun HistoryEssayTemplatesSection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("📝 රචනා සැකිලි", color = Color.White, fontWeight = FontWeight.Bold)
    Text("විභාගයේදී උපරිම ලකුණු ලබාගැනීමට අවශ්‍ය ඡේද සැකසුම සහ කරුණු පෙළගැස්වීම.", color = Color(0xFF94A3B8), fontSize = 11.sp)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706))) {
      Text("වසන්න (Close)", fontSize = 11.sp)
    }
  }
}
