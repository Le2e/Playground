package com.upr.mvvmsample.business.data.user.remote

import com.google.gson.annotations.SerializedName

data class UserAuthRsp(
    @SerializedName("token")
    val sessionToken: String?
)
