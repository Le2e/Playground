package com.upr.mvvmsample.business.data.user.remote

class AuthRemote {
    fun authenticateUser(username: String, password: String): UserAuthRsp {
        // todo - retrofit to make call
        return UserAuthRsp("FakeCloudToken")
    }
}