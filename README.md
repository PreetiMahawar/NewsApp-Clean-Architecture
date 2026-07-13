# News App

An Android news application built with Jetpack Compose and Kotlin, following Clean Architecture and the MVVM design pattern.

<br>

<p align="center">
<img width="1000" alt="clean_architecture" src="https://github.com/user-attachments/assets/c334dc22-9dda-4c33-9c22-371cdcb468a9">
</p>

## Major Highlights

- Built entirely with Jetpack Compose
- Kotlin
- Clean Architecture
- MVVM Design Pattern
- Multi-module architecture
- Dagger Hilt for Dependency Injection
- Retrofit for Networking
- Kotlin Coroutines + Flow
- StateFlow for UI State Management
- Navigation Compose
- Coil Image Loading
- AndroidX Browser for Reading Articles

## Features Implemented

- Browse the latest Top Headlines
- Explore categorized news by:
  - Country
  - Language
  - News Source
- Select exactly two languages to view randomized news articles
- Instant search implemented using Kotlin Flow
  - debounce
  - filter
  - distinctUntilChanged
  - flatMapLatest
- Read detailed news articles using AndroidX Browser

## Dependencies

- Jetpack Compose: Modern declarative UI toolkit for building native Android user interfaces
- Coil: Efficient image loading and caching library
- Retrofit: Type-safe HTTP client for seamless API network requests
- Gson: JSON serialization and deserialization library
- Dagger Hilt: Dependency injection framework for managing dependencies
- AndroidX Browser: Opens news  articles in a browser 
- Lifecycle Components: Lifecycle-aware components for managing UI state
- Hilt Navigation Compose: Hilt integration with Compose Navigation

## Screenshots

<table>
  <tr>
    <td> <img height="548" alt="top_headlines" src="https://github.com/user-attachments/assets/6fb038a0-7ee5-496f-bfb7-fd7f60bfb690"> </td>
    <td> <img height="548" alt="main_screen" src="https://github.com/user-attachments/assets/306dc009-06c4-497a-b376-856e61c20639"> </td>
    <td> <img height="548" alt="search_news" src="https://github.com/user-attachments/assets/c9c84a7a-e5df-4515-8b58-6eb0e1e539c0"> </td>
    <td> <img height="548" alt="detailed_news" src="https://github.com/user-attachments/assets/52f70fc7-055e-4c6e-8d50-cbdd3bf2d137"> </td>
  </tr>
</table>

<br>

## Project Folder Structure 

The project follows Clean Architecture with MVVM and is divided into multiple modules:

- ### app module

```
app/src/main/java
└── com
    └── preeti
        └── newsapp_clean_architecture
            └── NewsApplication.kt

```

- ### data module 

```
data/src/main/java/com/preeti/newsapp/data

├── api
│   ├── ApiKeyInterceptor.kt
│   └── NetworkService.kt
├── di
│   ├── module
│   │   ├── NetworkModule.kt
│   │   └── RepositoryModule.kt
│   └── qualifiers.kt
├── dispatcher
│   └── DefaultDispatcherProvider.kt
├── helper
│   └── AssetsHelper.kt
└── repository
    ├── CountriesRepositoryImpl.kt
    ├── LanguagesRepositoryImpl.kt
    ├── NewsByCountryRepositoryImpl.kt
    ├── NewsByLanguageRepositoryImpl.kt
    ├── NewsBySourceRepositoryImpl.kt
    ├── NewsByTwoLanguagesRepositoryImpl.kt
    ├── NewsSourcesRepositoryImpl.kt
    ├── SearchRepositoryImpl.kt
    └── TopHeadlineRepositoryImpl.kt

```

- ### domain module

```
domain/src/main/java/com/preeti/newsapp/domain

├── dispacther
│   └── DispatcherProvider.kt
├── model
│   ├── Article.kt
│   ├── Countries.kt
│   ├── Country.kt
│   ├── Language.kt
│   ├── Languages.kt
│   ├── NewsSource.kt
│   ├── NewsSourcesResponse.kt
│   ├── SearchRequest.kt
│   ├── Source.kt
│   └── TopHeadlinesResponse.kt
├── repository
│   ├── CountriesRepository.kt
│   ├── LanguagesRepository.kt
│   ├── NewsByCountryRepository.kt
│   ├── NewsByLanguageRepository.kt
│   ├── NewsBySourceRepository.kt
│   ├── NewsByTwoLanguagesRepository.kt
│   ├── NewsSourcesRepository.kt
│   ├── SearchRepository.kt
│   └── TopHeadlineRepository.kt
└── usecase
    ├── GetCountriesUseCase.kt
    ├── GetLanguagesUseCase.kt
    ├── GetNewsByCountryUseCase.kt
    ├── GetNewsByLanguageUseCase.kt
    ├── GetNewsBySourceUseCase.kt
    ├── GetNewsSourcesUseCase.kt
    ├── GetSearchUseCase.kt
    ├── GetTopHeadlineUseCase.kt
    ├── GetTwoLanguagesSelectUseCase.kt
    ├── GetTwoLanguagesUseCase.kt
    ├── SearchNewsUseCase.kt
    ├── SelectLanguagesUseCase.kt
    └── ValidateLanguagesUseCase.kt


```

- ### presentation module

```
presentation/src/main/java/com/preeti/newsapp/presentation

├── base
│   ├── CommonUI.kt
│   ├── GradientType.kt
│   ├── NewsNavigation.kt
│   └── UiState.kt
├── countries
│   ├── CountriesScreen.kt
│   └── CountriesViewModel.kt
├── languages
│   ├── LanguagesScreen.kt
│   ├── LanguagesViewModel.kt
│   ├── TwoLanguagesScreen.kt
│   └── TwoLanguagesViewModel.kt
├── main
│   └── MainActivity.kt
├── newsbycountry
│   ├── NewsByCountryScreen.kt
│   └── NewsByCountryViewModel.kt
├── newsbylanguage
│   ├── NewsByLanguageScreen.kt
│   ├── NewsByLanguageViewModel.kt
│   ├── NewsByTwoLanguagesScreen.kt
│   └── NewsByTwoLanguagesViewModel.kt
├── newsbysource
│   ├── NewsBySourceScreen.kt
│   └── NewsBySourceViewModel.kt
├── newssources
│   ├── NewsSourcesScreen.kt
│   └── NewsSourcesViewModel.kt
├── search
│   ├── SearchScreen.kt
│   └── SearchViewModel.kt
├── start
│   ├── ScreenType.kt
│   └── StartingScreen.kt
├── theme
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
└── topheadline
    ├── CommonTopHeadlineScreen.kt
    ├── TopHeadlineScreen.kt
    └── TopHeadlineViewModel.kt

```

- ### utils module

```
utils/src/main/java/com/preeti/newsapp/
└── utils
    └── AppConstant.kt

```

## How to run the project

- Clone the repository
```
git clone https://github.com/PreetiMahawar/NewsApp-Clean-Architecture.git
cd NewsApp-Clean-Architecture
```

- Sign up at newsapi.org and generate your API key.
- Open the AppConstant.kt file in the utils module.
- Locate the following line:
```
const val API_KEY = "API key"
```
- Replace "API key" with the API key you obtained from NewsAPI.
```
const val API_KEY = "Your API key"
```
- Build and run the app

## TODO

- Offline caching using Room
- Paging 3 integration
- Unit and UI testing
- Bookmark synchronization

## License 

```
 Copyright (C) 2026 Preeti Mahawar

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

```
