package com.upr.mvvmsample.business.domain.util

object StringUtils {
    fun isEmptyString(value: String?): Boolean {
        return value == null || value.trim().isEmpty()
    }
}