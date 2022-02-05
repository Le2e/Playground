package com.upr.mvvmsample.business.data

sealed class RepoResult<out T> {
    data class Success<out T>(val value: T) : RepoResult<T>()
    data class Error(val throwable: Throwable) : RepoResult<Nothing>()
}