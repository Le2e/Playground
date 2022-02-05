package com.upr.mvvmsample.business.domain.feature.user

import com.upr.mvvmsample.business.domain.util.StringUtils

object UserDataValidation {
    private const val USERNAME_MAX_LENGTH = 20
    private const val USERNAME_MIN_LENGTH = 5
    private const val PASSWORD_MAX_LENGTH = 12
    private const val PASSWORD_MIN_LENGTH = 6

    fun isValidUsername(username: String) {
        if (StringUtils.isEmptyString(username) || username.trim().length < USERNAME_MIN_LENGTH) {
            throw IllegalArgumentException("Username too short")
        }

        if (username.length > USERNAME_MAX_LENGTH) {
            throw IllegalArgumentException("Username too long")
        }
    }

    fun isValidPassword(password: String) {
        if (StringUtils.isEmptyString(password) || password.trim().length < PASSWORD_MIN_LENGTH) {
            throw IllegalArgumentException("Password too short")
        }

        if (password.length > PASSWORD_MAX_LENGTH) {
            throw IllegalArgumentException("Password too long")
        }

        // todo - validate password has special character and at least one capital
    }
}