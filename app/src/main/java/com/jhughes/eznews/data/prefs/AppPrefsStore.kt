package com.jhughes.eznews.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jhughes.eznews.common.data.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPrefsStore @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "AppPrefsStore")

    companion object {
        val PREF_THEME_KEY = stringPreferencesKey("pref_theme")
    }

    val getAppTheme: Flow<AppTheme> = context.dataStore.data
        .map { preferences ->
            try {
                preferences[PREF_THEME_KEY]?.let { AppTheme.valueOf(it) } ?: AppTheme.SYSTEM
            } catch (e: Exception) {
                AppTheme.SYSTEM
            }
        }

    suspend fun saveAppTheme(appTheme: AppTheme) {
        context.dataStore.edit { preferences ->
            preferences[PREF_THEME_KEY] = appTheme.name
        }
    }
}