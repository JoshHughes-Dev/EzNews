package com.jhughes.eznews.common

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.jhughes.eznews.article.ArticleViewModel
import com.jhughes.eznews.article.ui.ArticleScreen
import com.jhughes.eznews.common.navigation.defaultEnterTransition
import com.jhughes.eznews.common.navigation.defaultExitTransition
import com.jhughes.eznews.common.navigation.defaultPopEnterTransition
import com.jhughes.eznews.common.navigation.defaultPopExitTransition
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.news.NewsViewModel
import com.jhughes.eznews.news.ui.SearchResultsScreen
import com.jhughes.eznews.settings.SettingsViewModel
import com.jhughes.eznews.settings.ui.SettingsScreen

internal sealed class NavigationRoute(val route: String) {

    object NewsGraph : NavigationRoute("news") {
        object SearchResultsDestination : NavigationRoute("news.news")
        object ArticleDestination : NavigationRoute("news.article")
    }

    object SettingsDestination : NavigationRoute("settings")
}

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class,
)
@Composable
fun EzNewsCoordinator() {

    val navController = rememberAnimatedNavController()

    Log.d("ComposeTest", "EzNewsCoordinator compose")

    AnimatedNavHost(
        navController = navController,
        startDestination = NavigationRoute.NewsGraph.route,
        enterTransition = { defaultEnterTransition(initialState, targetState) },
        exitTransition = { defaultExitTransition(initialState, targetState) },
        popEnterTransition = { defaultPopEnterTransition() },
        popExitTransition = { defaultPopExitTransition() },
    ) {
        Log.d("ComposeTest", "nav host compose")

        newsGraph(navController)

        composable(NavigationRoute.SettingsDestination.route) {
            val viewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(viewModel, closeSettings = { navController.popBackStack() })
        }
    }
}

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
internal fun NavGraphBuilder.newsGraph(
    navController: NavController
) = navigation(
    startDestination = NavigationRoute.NewsGraph.SearchResultsDestination.route,
    route = NavigationRoute.NewsGraph.route
) {
    Log.d("ComposeTest", "newsGraph compose")

    composable(NavigationRoute.NewsGraph.SearchResultsDestination.route) {
        Log.d("ComposeTest", "search results route compose")
        val parentEntry =
            remember { navController.getBackStackEntry(NavigationRoute.NewsGraph.route) }
        val viewModel: NewsViewModel = hiltViewModel(parentEntry)
        SearchResultsScreen(
            viewModel = viewModel,
            onArticleDetails = {
                viewModel.selectedArticle = it
                navController.navigate(NavigationRoute.NewsGraph.ArticleDestination.route)
            },
            onSettings = {
                navController.navigate(NavigationRoute.SettingsDestination.route)
            }
        )
    }

    composable(
        route = NavigationRoute.NewsGraph.ArticleDestination.route
    ) {
        val viewModel: ArticleViewModel = hiltViewModel()

        ArticleScreen(viewModel) {
            viewModel.article = Article.empty()
            navController.popBackStack()
        }
    }
}