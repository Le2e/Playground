package com.upr.mvvmsample.business.domain.feature.user.validation

import com.upr.mvvmsample.business.domain.util.StringUtils

/**
 * Responsibility: define rules that constitute a valid password field for a user
 * Reason to change:
 *      - rule change to what a valid password is
 */
class UserPasswordValidationRule(private val password: String) : UserFieldValidationRule {
    private companion object {
        private const val PASSWORD_MAX_LENGTH = 12
        private const val PASSWORD_MIN_LENGTH = 6
    }

    override fun validateField() {
        if (StringUtils.isEmptyString(password) || password.trim().length < PASSWORD_MIN_LENGTH) {
            throw IllegalArgumentException("Password too short")
        }

        if (password.length > PASSWORD_MAX_LENGTH) {
            throw IllegalArgumentException("Password too long")
        }

        // todo - validate password has special character and at least one capital
    }
}

