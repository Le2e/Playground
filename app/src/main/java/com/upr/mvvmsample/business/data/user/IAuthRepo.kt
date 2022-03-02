package com.upr.mvvmsample.business.data.user

import com.upr.mvvmsample.business.data.RepoResult
import com.upr.mvvmsample.business.domain.feature.user.model.UserSession
import io.reactivex.rxjava3.core.Observable

interface IAuthRepo {
    fun authUser(username: String, password: String): RepoResult<UserSession>

    fun authUserObs(username: String, password: String): Observable<RepoResult<UserSession>>
}