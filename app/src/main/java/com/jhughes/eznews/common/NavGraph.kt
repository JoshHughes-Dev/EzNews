package com.jhughes.eznews.common

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.jhughes.eznews.common.data.ArticleParcel
import com.jhughes.eznews.common.data.toParcel
import com.jhughes.eznews.common.utils.Navigator
import com.jhughes.eznews.domain.model.Article
import kotlinx.parcelize.Parcelize

/**
 * Models the screens in the app and any arguments they require.
 */
sealed class Destination : Parcelable {
    @Parcelize
    object TopHeadlines : Destination()

    @Parcelize
    object Settings : Destination()

    @Immutable
    @Parcelize
    data class ArticleDetails(val article: ArticleParcel) : Destination()
}

/**
 * Models the navigation actions in the app.
 */
class Actions(navigator: Navigator<Destination>) {

    val showSettings: () -> Unit = {
        navigator.navigate(Destination.Settings)
    }
    val selectArticle: (Article) -> Unit = { article: Article ->
        navigator.navigate(Destination.ArticleDetails(article.toParcel()))
    }
    val upPress: () -> Unit = {
        navigator.back()
    }
}