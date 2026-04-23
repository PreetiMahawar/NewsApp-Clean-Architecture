package com.preeti.newsapp.presentation.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.theme.BlueShade1
import com.preeti.newsapp.presentation.theme.BlueShade2
import com.preeti.newsapp.presentation.theme.BlueShade3
import com.preeti.newsapp.presentation.theme.DarkRed
import com.preeti.newsapp.presentation.theme.PinkShade1
import com.preeti.newsapp.presentation.theme.PinkShade2
import com.preeti.newsapp.presentation.theme.PinkShade3
import com.preeti.newsapp.presentation.theme.PurpleShade1
import com.preeti.newsapp.presentation.theme.PurpleShade2
import com.preeti.newsapp.presentation.theme.PurpleShade3
import com.preeti.newsapp.presentation.theme.YellowShade1
import com.preeti.newsapp.presentation.theme.YellowShade2
import com.preeti.newsapp.presentation.theme.YellowShade3
import com.preeti.newsapp.presentation.theme.customFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(title: String, fontSize: TextUnit = 24.sp) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = {
            Text(
                text = title,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                textAlign = TextAlign.Center,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBarCenterAligned(
    title: String, fontSize: TextUnit = 24.sp, onBackNavigation: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = {
            Text(
                text = title,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackNavigation() }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_arrow_back),
                    contentDescription = "Icon Back Button"
                )
            }
        },
    )
}


@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val contentDesc = stringResource(R.string.loading)
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .semantics {
                    contentDescription = contentDesc
                })
    }
}

@Composable
fun ShowError(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = text,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Bold,
            color = DarkRed,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(14.dp)
        )
    }
}

@Composable
fun gradientBackgroundBrush(
    isVerticalGradient: Boolean, colors: List<Color>
): Brush {

    val endOffset = if (isVerticalGradient) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors, start = Offset.Zero, end = endOffset
    )
}
