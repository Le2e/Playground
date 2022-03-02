package com.upr.mvvmsample.framework.data.user.remote

import com.upr.mvvmsample.business.data.user.remote.IAuthRemote
import com.upr.mvvmsample.business.data.user.remote.UserAuthRsp

class AuthRemote: IAuthRemote {
    override fun authenticateUser(username: String, password: String): UserAuthRsp {
        // todo - retrofit to make call
        return UserAuthRsp("FakeCloudToken")
    }
}