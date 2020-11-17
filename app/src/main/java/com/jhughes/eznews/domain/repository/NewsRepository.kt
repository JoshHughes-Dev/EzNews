package com.jhughes.eznews.domain.repository

import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    fun getDummyData() : Flow<String>
}