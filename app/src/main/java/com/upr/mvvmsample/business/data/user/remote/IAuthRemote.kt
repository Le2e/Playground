package com.upr.mvvmsample.business.data.user.remote

interface IAuthRemote {
    fun authenticateUser(username: String, password: String): UserAuthRsp
}