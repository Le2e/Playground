package com.upr.mvvmsample.business.data.user

import com.upr.mvvmsample.business.data.user.local.AuthEntity
import com.upr.mvvmsample.business.data.user.remote.UserAuthRsp
import com.upr.mvvmsample.business.domain.feature.user.model.UserSession

class AuthAdapter {
    fun toModel(authRsp: UserAuthRsp): UserSession {
        if (authRsp.sessionToken == null) throw IllegalArgumentException("Session Not Created")
        return UserSession(authRsp.sessionToken)
    }

    fun toEntity(authRsp: UserAuthRsp): AuthEntity {
        if (authRsp.sessionToken == null) throw IllegalArgumentException("Session Not Created")
        return AuthEntity(authRsp.sessionToken)
    }
}