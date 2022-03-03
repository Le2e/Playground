package com.upr.mvvmsample.business.domain.feature.user.validation

/**
 * Responsibility: run validation rules on a set of user fields provided
 * Reason to change:
 *      - how an invalid field is returned into the requesting flow
 */
class UserDataValidation : IUserDataValidation {

    override fun validateUserFields(vararg fields: UserFieldValidationRule): UserValidationResult {
        if (fields.isEmpty()) {
            return UserValidationResult.Valid
        }

        fields.forEach {
            try {
                it.validateField()
            } catch (ex: IllegalArgumentException) {
                return UserValidationResult.Invalid(ex.message ?: "")
            }
        }

        return UserValidationResult.Valid
    }
}
