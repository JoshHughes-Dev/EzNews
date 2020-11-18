package com.jhughes.eznews.domain

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()
    data class Error(val error: Throwable) : ApiResult<Nothing>()
}