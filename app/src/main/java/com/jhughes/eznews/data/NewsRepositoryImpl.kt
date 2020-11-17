package com.jhughes.eznews.data

import com.jhughes.eznews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    override fun getDummyData(): Flow<String> {
        return flow {
            emit("EzNews")
        }
    }
}