package com.jhughes.eznews.common

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.jhughes.eznews.articledetails.ui.ArticleDetails
import com.jhughes.eznews.headlines.HeadlinesViewModel
import com.jhughes.eznews.headlines.ui.SelectCategory
import com.jhughes.eznews.headlines.ui.SelectCountry
import com.jhughes.eznews.headlines.ui.TopHeadlines
import com.jhughes.eznews.settings.ui.SettingsLayout

internal sealed class NavigationRoute(val route: String) {

    object HomeGraph : NavigationRoute("home") {
        object HeadlinesScreen : NavigationRoute("home.headlines")
        object SelectCountryDialog : NavigationRoute("home.select_country")
        object SelectCategoryDialog : NavigationRoute("home.select_category")
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

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.HomeGraph.route
        ) {
            homeGraph(navController)

            composable(NavigationRoute.SettingsScreen.route) {
                SettingsLayout(closeSettings = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
internal fun NavGraphBuilder.homeGraph(
    navController: NavController
) {

    navigation(
        startDestination = NavigationRoute.HomeGraph.HeadlinesScreen.route,
        route = NavigationRoute.HomeGraph.route
    ) {
        composable(NavigationRoute.HomeGraph.HeadlinesScreen.route) {
            val parentEntry =
                remember { navController.getBackStackEntry(NavigationRoute.HomeGraph.route) }
            val viewModel: HeadlinesViewModel = hiltViewModel(parentEntry)
            TopHeadlines(
                viewModel = viewModel,
                onSelectCategory = { navController.navigate(NavigationRoute.HomeGraph.SelectCategoryDialog.route) },
                onSelectCountry = { navController.navigate(NavigationRoute.HomeGraph.SelectCountryDialog.route) },
                onArticleDetails = {
                    viewModel.selectedArticle = it
                    navController.navigate(NavigationRoute.HomeGraph.ArticleDetails.route)
                },
                onSettings = {
                    navController.navigate(NavigationRoute.SettingsScreen.route)
                }
            )
        }
        bottomSheet(NavigationRoute.HomeGraph.SelectCategoryDialog.route) {
            val parentEntry =
                remember { navController.getBackStackEntry(NavigationRoute.HomeGraph.route) }
            val viewModel: HeadlinesViewModel = hiltViewModel(parentEntry)
            SelectCategory(modifier = Modifier.navigationBarsPadding()) { category ->
                viewModel.setNewsCategory(category)
                navController.popBackStack()
            }
        }
        bottomSheet(NavigationRoute.HomeGraph.SelectCountryDialog.route) {
            val parentEntry =
                remember { navController.getBackStackEntry(NavigationRoute.HomeGraph.route) }
            val viewModel: HeadlinesViewModel = hiltViewModel(parentEntry)
            SelectCountry(modifier = Modifier.navigationBarsPadding()) { country ->
                viewModel.setCountry(country)
                navController.popBackStack()
            }
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
}