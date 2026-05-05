package com.preeti.newsapp.presentation.countries

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
import com.preeti.newsapp.domain.model.Country
import com.preeti.newsapp.presentation.R
import com.preeti.newsapp.presentation.base.CustomAppBarCenterAligned
import com.preeti.newsapp.presentation.base.GradientType
import com.preeti.newsapp.presentation.base.ShowError
import com.preeti.newsapp.presentation.base.ShowLoading
import com.preeti.newsapp.presentation.base.UiState
import com.preeti.newsapp.presentation.base.gradientBackgroundBrush
import com.preeti.newsapp.presentation.theme.customFontFamily

@Composable
fun CountriesRoute(
    titleAppBar: String = stringResource(R.string.countries),
    onCountryClick: (id: String) -> Unit,
    onBackNavigation: () -> Unit,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        CustomAppBarCenterAligned(
            title = titleAppBar, fontSize = 20.sp, onBackNavigation = onBackNavigation
        )
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            CountriesScreen(uiState, onCountryClick)
        }
    })
}

@Composable
fun CountriesScreen(uiState: UiState<List<Country>>, onCountryClick: (id: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            CountryList(uiState.data, onCountryClick)
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
fun CountryList(countries: List<Country>, onCountryClick: (id: String) -> Unit) {
    LazyColumn(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
        items(countries, key = { country -> country.id }) { language ->
            Country(language, onCountryClick, gradientColorList = GradientType.PINK.colors())
        }
    }
}

@Composable
fun Country(
    country: Country, onLanguageClick: (id: String) -> Unit, gradientColorList: List<Color>
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
                    onLanguageClick(country.id)
                }
                .padding(14.dp)) {

            CountryText(country.name)
        }

    }
}

@Composable
fun CountryText(countryName: String?) {
    if (!countryName.isNullOrEmpty()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = countryName,
            maxLines = 2,
            color = MaterialTheme.colorScheme.onPrimary,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
    }
}
