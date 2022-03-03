package com.upr.mvvmsample.business.domain.feature.user.validation

sealed class UserValidationResult {
    object Valid : UserValidationResult()
    data class Invalid(val reason: String) : UserValidationResult()
}