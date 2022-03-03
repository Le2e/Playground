package com.upr.mvvmsample.business.domain.feature.user.validation

interface IUserDataValidation {
    fun validateUserFields(vararg fields: UserFieldValidationRule): UserValidationResult
}