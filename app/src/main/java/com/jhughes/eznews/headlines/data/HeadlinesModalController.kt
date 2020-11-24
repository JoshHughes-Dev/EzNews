package com.jhughes.eznews.headlines.data

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState

@OptIn(ExperimentalMaterialApi::class)
class HeadlinesModalController(
    private val categorySheetState : ModalBottomSheetState,
    private val countrySheetState : ModalBottomSheetState
) {

    fun showCategoryFilterModal() {
        categorySheetState.show()
    }

    fun showCountryFilterModal() {
        countrySheetState.show()
    }
}