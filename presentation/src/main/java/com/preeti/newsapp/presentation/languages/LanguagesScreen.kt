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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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


@Composable
fun LanguagesRoute(
    titleAppBar: String = stringResource(R.string.languages),
    onLanguageClick: (id: String) -> Unit,
    onBackNavigation: () -> Unit,
    viewModel: LanguagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        CustomAppBarCenterAligned(
            title = titleAppBar, fontSize = 20.sp, onBackNavigation = onBackNavigation
        )
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LanguagesScreen(uiState, onLanguageClick)
        }
    })
}

@Composable
fun LanguagesScreen(uiState: UiState<List<Language>>, onLanguageClick: (id: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            LanguageList(uiState.data, onLanguageClick)
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
fun LanguageList(languages: List<Language>, onLanguageClick: (id: String) -> Unit) {
    LazyColumn(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
        items(languages, key = { language -> language.id }) { language ->
            Language(language, onLanguageClick, gradientColorList = GradientType.PINK.colors())
        }
    }
}

@Composable
fun Language(
    language: Language, onLanguageClick: (id: String) -> Unit, gradientColorList: List<Color>
) {
    Column {
        Spacer(modifier = Modifier.height(14.dp))
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
                    onLanguageClick(language.id)
                }
                .padding(14.dp)) {

            LanguageText(language.name)
        }

    }
}

@Composable
fun LanguageText(languageName: String?) {
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
