package com.jhughes.eznews.common

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.jhughes.eznews.articledetails.ui.ArticleDetails
import com.jhughes.eznews.headlines.HeadlinesViewModel
import com.jhughes.eznews.headlines.ui.TopHeadlines
import com.jhughes.eznews.settings.ui.SettingsLayout

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

    val navController = rememberNavController()

    Log.d("ComposeTest", "EzNewsCoordinator compose")

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.HomeGraph.route
    ) {
        Log.d("ComposeTest", "nav host compose")

        homeGraph(navController)

        composable(NavigationRoute.SettingsScreen.route) {
            SettingsLayout(closeSettings = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
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