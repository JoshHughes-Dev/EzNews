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
import com.jhughes.eznews.articledetails.ui.ArticleDetails
import com.jhughes.eznews.common.navigation.defaultEnterTransition
import com.jhughes.eznews.common.navigation.defaultExitTransition
import com.jhughes.eznews.common.navigation.defaultPopEnterTransition
import com.jhughes.eznews.common.navigation.defaultPopExitTransition
import com.jhughes.eznews.headlines.HeadlinesViewModel
import com.jhughes.eznews.headlines.ui.TopHeadlines
import com.jhughes.eznews.settings.SettingsViewModel
import com.jhughes.eznews.settings.ui.SettingsScreen

internal sealed class NavigationRoute(val route: String) {

    object HomeGraph : NavigationRoute("home") {
        object HeadlinesScreen : NavigationRoute("home.headlines")
        object ArticleDetails : NavigationRoute("home.details")
    }

    object SettingsScreen : NavigationRoute("settings")
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
        startDestination = NavigationRoute.HomeGraph.route,
        enterTransition = { defaultEnterTransition(initialState, targetState) },
        exitTransition = { defaultExitTransition(initialState, targetState) },
        popEnterTransition = { defaultPopEnterTransition() },
        popExitTransition = { defaultPopExitTransition() },
    ) {
        Log.d("ComposeTest", "nav host compose")

        homeGraph(navController)

        composable(NavigationRoute.SettingsScreen.route) {
            val viewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(viewModel, closeSettings = { navController.popBackStack() })
        }
    }
}

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
internal fun NavGraphBuilder.homeGraph(
    navController: NavController
) = navigation(
    startDestination = NavigationRoute.HomeGraph.HeadlinesScreen.route,
    route = NavigationRoute.HomeGraph.route
) {
    Log.d("ComposeTest", "homeGraph compose")

    composable(NavigationRoute.HomeGraph.HeadlinesScreen.route) {
        Log.d("ComposeTest", "headlines route compose")
        val parentEntry =
            remember { navController.getBackStackEntry(NavigationRoute.HomeGraph.route) }
        val viewModel: HeadlinesViewModel = hiltViewModel(parentEntry)
        TopHeadlines(
            viewModel = viewModel,
            onArticleDetails = {
                viewModel.selectedArticle = it
                navController.navigate(NavigationRoute.HomeGraph.ArticleDetails.route)
            },
            onSettings = {
                navController.navigate(NavigationRoute.SettingsScreen.route)
            }
        )
    }

    composable(
        route = NavigationRoute.HomeGraph.ArticleDetails.route
    ) {
        val parentEntry =
            remember { navController.getBackStackEntry(NavigationRoute.HomeGraph.route) }
        val viewModel: HeadlinesViewModel = hiltViewModel(parentEntry)

        ArticleDetails(viewModel) {
            navController.popBackStack()
        }
    }
}