package com.jhughes.eznews.common.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.jhughes.eznews.common.data.ArticleParcel
import com.squareup.moshi.Moshi

class AssetParamType(private val moshi : Moshi) : NavType<ArticleParcel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ArticleParcel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ArticleParcel {
        val jsonAdapter = moshi.adapter(ArticleParcel::class.java).lenient()
        return jsonAdapter.fromJson(value)!!
    }

    override fun put(bundle: Bundle, key: String, value: ArticleParcel) {
        bundle.putParcelable(key, value)
    }
}