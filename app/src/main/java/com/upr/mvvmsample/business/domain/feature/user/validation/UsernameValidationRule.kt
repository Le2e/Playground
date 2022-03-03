package com.upr.mvvmsample.business.domain.feature.user.validation

import com.upr.mvvmsample.business.domain.util.StringUtils

/**
 * Responsibility: define rules that constitute a valid username field for a user
 * Reason to change:
 *      - rule change to what a valid username is
 */
class UsernameValidationRule(private val username: String) : UserFieldValidationRule {
    private companion object {
        private const val USERNAME_MAX_LENGTH = 20
        private const val USERNAME_MIN_LENGTH = 5
    }

    override fun validateField() {
        if (StringUtils.isEmptyString(username) || username.trim().length < USERNAME_MIN_LENGTH) {
            throw IllegalArgumentException("Username too short")
        }

        if (username.length > USERNAME_MAX_LENGTH) {
            throw IllegalArgumentException("Username too long")
        }
    }
}