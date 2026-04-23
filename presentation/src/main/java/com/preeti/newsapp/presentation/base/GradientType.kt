package com.preeti.newsapp.presentation.base

import androidx.compose.ui.graphics.Color
import com.preeti.newsapp.presentation.theme.BlueShade1
import com.preeti.newsapp.presentation.theme.BlueShade2
import com.preeti.newsapp.presentation.theme.BlueShade3
import com.preeti.newsapp.presentation.theme.PinkShade1
import com.preeti.newsapp.presentation.theme.PinkShade2
import com.preeti.newsapp.presentation.theme.PinkShade3
import com.preeti.newsapp.presentation.theme.PurpleShade1
import com.preeti.newsapp.presentation.theme.PurpleShade2
import com.preeti.newsapp.presentation.theme.PurpleShade3
import com.preeti.newsapp.presentation.theme.YellowShade1
import com.preeti.newsapp.presentation.theme.YellowShade2
import com.preeti.newsapp.presentation.theme.YellowShade3

enum class GradientType {

    BLUE, PINK, PURPLE, YELLOW;

    fun colors(): List<Color> {

        return when (this) {

            YELLOW -> listOf(YellowShade1, YellowShade2, YellowShade3)

            PURPLE -> listOf(PurpleShade1, PurpleShade2, PurpleShade3)

            BLUE -> listOf(BlueShade1, BlueShade2, BlueShade3)

            PINK -> listOf(PinkShade1, PinkShade2, PinkShade3)

            // else -> listOf(BlueShade1, BlueShade2, BlueShade3)
        }
    }
}