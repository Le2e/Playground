package com.upr.mvvmsample.framework.ui.common

sealed class ViewResult<out T> {
    data class Success<out T>(val value: T): ViewResult<T>()
    data class Failure(val details: FailDetails) : ViewResult<Nothing>()

    data class FailDetails(val title: String, val message: String?)
}