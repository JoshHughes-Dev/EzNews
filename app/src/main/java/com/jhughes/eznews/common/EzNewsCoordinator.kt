package com.jhughes.eznews.common

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jhughes.eznews.articledetails.ui.ArticleDetails
import com.jhughes.eznews.common.data.ArticleParcel
import com.jhughes.eznews.common.data.toDomain
import com.jhughes.eznews.common.data.toParcel
import com.jhughes.eznews.common.utils.AssetParamType
import com.jhughes.eznews.domain.model.Article
import com.jhughes.eznews.headlines.HeadlinesViewModel
import com.jhughes.eznews.headlines.ui.TopHeadlines
import com.jhughes.eznews.settings.ui.SettingsLayout
import com.squareup.moshi.Moshi

object ExNewsDestinations {
    const val TOP_HEADLINES: String = "top_headlines"
    const val SETTINGS: String = "settings"
    const val ARTICLE_DETAILS: String = "article_details"
}

/**
 * Models the navigation actions in the app.
 */
class EzNewsNavigationActions(navController: NavHostController, val moshi : Moshi) {
    val navigateToTopHeadlines: () -> Unit = {
        navController.navigate(ExNewsDestinations.TOP_HEADLINES) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToSettings: () -> Unit = {
        navController.navigate(ExNewsDestinations.SETTINGS) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToArticleDetails: (Article) -> Unit = {
        val json = Uri.encode(moshi.adapter(ArticleParcel::class.java).toJson(it.toParcel()))
        val destination = ExNewsDestinations.ARTICLE_DETAILS + "/" + json
        navController.navigate(destination)
    }
}

@Composable
fun EzNewsCoordinator(
    navController: NavHostController = rememberNavController(),
    moshi : Moshi,
) {
    val navigationActions = remember(navController) {
        EzNewsNavigationActions(navController, moshi)
    }

    NavHost(
        navController = navController,
        startDestination = ExNewsDestinations.TOP_HEADLINES
    ) {
        composable(ExNewsDestinations.TOP_HEADLINES) {
            val headlinesViewModel = hiltViewModel<HeadlinesViewModel>()
            TopHeadlines(viewModel = headlinesViewModel, navigationActions)
        }
        composable(ExNewsDestinations.SETTINGS) {
            SettingsLayout(closeSettings = { navController.navigateUp() })
        }
        composable(
            route = "${ExNewsDestinations.ARTICLE_DETAILS}/{article}",
            arguments = listOf(navArgument("article") { type = AssetParamType(moshi) })
        ) {
            val article = it.arguments?.getParcelable<ArticleParcel>("article")!!.toDomain()
            ArticleDetails(article = article) { navController.navigateUp() }
        }
    }
}