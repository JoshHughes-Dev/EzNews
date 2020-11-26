![eznews_logo](art/eznews_logo_light.png) 


# EzNews

EzNews is an android app showing a simple news feed built using Jetpack Compose, Paging 3 and the NewsAPI.org.

![eznews_demo](art/eznews_demo.gif)

This is a personal project for me to try out the latest libraries for Jetpack Compose (Alpha-07) and Paging 3. This app isnt publically released on any app store.

- 100% Kotlin
- MVVM architecture
- Uses Kotlin coroutines thoroughout
- Uses Hilt for dependency injection
- UI built using Jetpack compose

# Requirements

You will require the latest Android Studio 4.2 preview to build the app. This is because Compose is only supported in v4.2 or later.

## Api Key

You will need to supply your own development API key for NewsAPI.org. see [NewsAPI](https://newsapi.org/) for details.

Once you obtain a key, you can set it in your `local.properties` gradle file like so:

```
# API key used to access news api
NEWS_ORG_API_KEY={key}
```

# Libraries

- Jetpack Compose
- Retrofit
- ViewModel
- Coroutines
- Hilt
- Moshi
- Accompanist (by Chris Banes)
- NewsAPI
- Paging Compose

# Features

- Paged data with states for loading / error / next page loading / next page error.
- Filter controls for news categories and for country.
- Light/Dark Compose theming and custom typography.

## Filters

![eznews_demo_filters](art/eznews_demo_filters.gif)

## Dark theme

![eznews_demo_dark_theme](art/eznews_demo_dark_theme.gif)


