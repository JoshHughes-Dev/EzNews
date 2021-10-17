package com.jhughes.eznews.headlines.data

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
class HeadlinesModalController(
    private val coroutineScope : CoroutineScope,
    private val categorySheetState : ModalBottomSheetState,
    private val countrySheetState : ModalBottomSheetState
) {

    fun showCategoryFilterModal() {
        coroutineScope.launch { categorySheetState.show() }
    }

    fun showCountryFilterModal() {
        coroutineScope.launch { countrySheetState.show() }
    }
}