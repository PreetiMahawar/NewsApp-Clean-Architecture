package com.preeti.newsapp.presentation.start

sealed interface ScreenType {

    data object TopHeadlines : ScreenType
    data object NewsSources : ScreenType
    data object Languages : ScreenType
    data object TwoLanguages : ScreenType
    data object Countries : ScreenType
    data object Search : ScreenType

}