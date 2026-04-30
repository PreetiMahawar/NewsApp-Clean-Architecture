package com.preeti.newsapp.presentation.languages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.preeti.newsapp.domain.model.Language
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.base.CustomAppBarCenterAligned
import com.preeti.newsapp.presentation.base.GradientType
import com.preeti.newsapp.presentation.base.ShowError
import com.preeti.newsapp.presentation.base.ShowLoading
import com.preeti.newsapp.presentation.base.UiState
import com.preeti.newsapp.presentation.base.gradientBackgroundBrush
import com.preeti.newsapp.presentation.theme.customFontFamily

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TwoLanguagesRoute(
    titleAppBar: String = stringResource(R.string.languages),
    onLanguageSelect: (String, String) -> Unit,
    onBackNavigation: () -> Unit,
    viewModel: TwoLanguagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedIds by viewModel.selectedIds.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, topBar = {
        CustomAppBarCenterAligned(
            title = titleAppBar, fontSize = 20.sp, onBackNavigation = onBackNavigation
        )
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Spacer(modifier = Modifier.height(14.dp))
            TwoLanguagesScreen(
                uiState, selectedIds, viewModel, modifier = Modifier.weight(1f)
            )
            SelectButton(selectedIds, stringResource(R.string.select_language), onLanguageSelect, viewModel, snackbarHostState, scope)
        }
    })
}

@Composable
fun TwoLanguagesScreen(
    uiState: UiState<List<Language>>,
    selectedIds: Set<String>,
    viewModel: TwoLanguagesViewModel,
    modifier: Modifier
) {
    when (uiState) {
        is UiState.Success -> {
            TwoLanguageList(uiState.data, selectedIds, viewModel, modifier)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }
    }
}

@Composable
fun TwoLanguageList(
    languages: List<Language>,
    selectedIds: Set<String>,
    viewModel: TwoLanguagesViewModel,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier.padding(start = 30.dp, end = 30.dp)) {
        items(languages, key = { language -> language.id }) { language ->
            LanguageItem(
                language = language,
                gradientColorList = if (selectedIds.contains(language.id)) GradientType.BLUE.colors() else GradientType.PINK.colors(),
                    viewModel = viewModel
                )
        }
    }
}

@Composable
fun LanguageItem(
    language: Language, gradientColorList: List<Color>, viewModel: TwoLanguagesViewModel
) {
    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(0.dp, MaterialTheme.colorScheme.tertiary),
                    RoundedCornerShape(10.dp)
                )
                .background(
                    brush = gradientBackgroundBrush(true, gradientColorList),
                    shape = RoundedCornerShape(10.dp),
                    alpha = 0.8f
                )
                .clickable {
                    viewModel.onLanguageSelect(language.id)
                }
                .padding(14.dp)) {
            LanguageItemText(language.name)
        }

        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Composable
fun LanguageItemText(languageName: String?) {

    if (!languageName.isNullOrEmpty()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = languageName,
            maxLines = 2,
            color = MaterialTheme.colorScheme.onPrimary,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SelectButton(
    selectedIds: Set<String>,
    selectMsg: String,
    onLanguageSelect: (String, String) -> Unit,
    viewModel: TwoLanguagesViewModel,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    Button(
        onClick = {
            performSelection(snackbarHostState, scope, selectMsg, selectedIds, viewModel, onLanguageSelect)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 100.dp, end = 100.dp, top = 14.dp, bottom = 14.dp)
    ) {
        Text(
            text = "GO",
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun performSelection(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    selectMsg: String,
    selectedIds: Set<String>,
    viewModel: TwoLanguagesViewModel,
    onLanguageSelect: (String, String) -> Unit
) {
    if (!viewModel.checkSelection()) {
        scope.launch { snackbarHostState.showSnackbar(selectMsg) }
    } else {
        onLanguageSelect(selectedIds.elementAt(0), selectedIds.elementAt(1))
    }
}

