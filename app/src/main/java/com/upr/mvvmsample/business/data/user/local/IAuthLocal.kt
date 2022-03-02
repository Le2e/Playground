package com.upr.mvvmsample.business.data.user.local

import com.upr.mvvmsample.business.data.RepoResult
import io.reactivex.rxjava3.core.Observable

interface IAuthLocal {
    fun saveUserSession(entity: AuthEntity): RepoResult<Boolean>

    fun saveUserSessionObs(entity: AuthEntity): Observable<RepoResult<Boolean>>
}