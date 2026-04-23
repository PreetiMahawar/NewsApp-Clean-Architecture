package com.preeti.newsapp.presentation.start

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.base.CustomAppBar
import com.preeti.newsapp.presentation.base.GradientType

import com.preeti.newsapp.presentation.base.gradientBackgroundBrush
import com.preeti.newsapp.presentation.theme.customFontFamily
import com.preeti.newsapp.utils.AppConstant

@Composable
fun StartingRoute(onClick: (name: String) -> Unit) {
    Scaffold(topBar = {
        CustomAppBar(title = AppConstant.APP_NAME)
    }, content = { padding ->
        StartingScreen(modifier = Modifier.padding(padding), onClick)
    })
}


@Composable
fun StartingScreen(modifier: Modifier, onClick: (name: String) -> Unit) {
    val scrollState: ScrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(start = 30.dp, end = 30.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Elements(
            title = stringResource(R.string.top_headlines),
            description = stringResource(R.string.desc_top_headlines),
            GradientType.PINK.colors(),
            onClick
        )
        Elements(
            title = stringResource(R.string.news_sources),
            description = stringResource(R.string.news_by_source),
            GradientType.BLUE.colors(),
            onClick
        )
        Elements(
            title = stringResource(R.string.languages),
            description = stringResource(R.string.desc_news_by_languages),
            GradientType.PINK.colors(),
            onClick
        )
        Elements(
            title = stringResource(R.string.two_languages),
            description = stringResource(R.string.desc_news_by_languages),
            GradientType.BLUE.colors(),
            onClick
        )
        Elements(
            title = stringResource(R.string.countries),
            description = stringResource(R.string.desc_news_by_country),
            GradientType.PINK.colors(),
            onClick
        )
        Elements(
            title = stringResource(R.string.search),
            stringResource(R.string.search_news),
            GradientType.BLUE.colors(),
            onClick
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun Elements(
    title: String,
    description: String,
    gradientColorList: List<Color>,
    onClick: (name: String) -> Unit
) {

    Column(
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
                onClick(title)
            }
            .padding(20.dp)) {

        TitleText(title)
        DescriptionText(description)
    }

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun TitleText(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = title,
        maxLines = 2,
        color = MaterialTheme.colorScheme.onPrimary,
        fontFamily = customFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun DescriptionText(description: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = description,
        maxLines = 2,
        color = MaterialTheme.colorScheme.onSecondary,
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp,
        textAlign = TextAlign.Center
    )
}




