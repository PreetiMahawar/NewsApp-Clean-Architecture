package com.preeti.newsapp.presentation.topheadline

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.preeti.newsapp.domain.model.Article
import com.preeti.newsapp.domain.model.Source
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.base.ShowError
import com.preeti.newsapp.presentation.base.ShowLoading
import com.preeti.newsapp.presentation.base.UiState
import com.preeti.newsapp.presentation.theme.customFontFamily

@Composable
fun TopHeadlineScreen(uiState: UiState<List<Article>>, onNewsClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            ArticleList(uiState.data, onNewsClick)
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
fun ArticleList(articles: List<Article>, onNewsClick: (url: String) -> Unit) {
    if (articles.isEmpty()) {
        ShowError(stringResource(R.string.no_data))
        return
    }
    LazyColumn(modifier = Modifier.padding(start = 14.dp, end = 14.dp)) {
        items(articles, key = { article -> article.url }) { article ->
            Article(article, onNewsClick)
        }

    }
}

@Composable
fun Article(article: Article, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (article.url.isNotEmpty()) {
                onNewsClick(article.url)
            }
        }) {
        Spacer(modifier = Modifier.height(14.dp))
        BannerImage(article)
        TitleText(article.title)
        DescriptionText(article.description)
        SourceText(article.source)
    }
}

@Composable
fun BannerImage(article: Article) {
    ElevatedCard(
        modifier = Modifier.border(
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.tertiary),
            RoundedCornerShape(10.dp)
        )
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun TitleText(title: String) {
    if (title.isNotEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = title,
            maxLines = 2,
            color = MaterialTheme.colorScheme.onPrimary,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        )
    }
}

@Composable
fun DescriptionText(description: String?) {
    if (!description.isNullOrEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp),
            text = description,
            maxLines = 2,
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        )
    }
}

@Composable
fun SourceText(source: Source) {
    Text(
        text = source.name,
        color = MaterialTheme.colorScheme.onPrimary,
        maxLines = 1,
        fontSize = 12.sp,
        fontFamily = customFontFamily,
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp, bottom = 4.dp),
    )
}