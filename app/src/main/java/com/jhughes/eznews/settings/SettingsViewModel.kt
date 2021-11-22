package com.jhughes.eznews.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jhughes.eznews.data.prefs.AppPrefsStore
import com.jhughes.eznews.common.data.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val appsPrefsStore: AppPrefsStore
) : ViewModel() {

    val appTheme = appsPrefsStore.getAppTheme

    suspend fun saveAppThemeTheme(appTheme: AppTheme) = appsPrefsStore.saveAppTheme(appTheme)
}