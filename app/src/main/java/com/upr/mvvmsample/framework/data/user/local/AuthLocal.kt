package com.upr.mvvmsample.framework.data.user.local

import com.upr.mvvmsample.business.data.RepoResult
import com.upr.mvvmsample.business.data.user.local.AuthEntity
import com.upr.mvvmsample.business.data.user.local.IAuthLocal
import io.reactivex.rxjava3.core.Observable

class AuthLocal : IAuthLocal {
    override fun saveUserSession(entity: AuthEntity): RepoResult<Boolean> {
        // todo - actually implement persistent storage
        return RepoResult.Success(true)
    }

    override fun saveUserSessionObs(entity: AuthEntity): Observable<RepoResult<Boolean>> {
        return Observable.defer {
            return@defer Observable.just(saveUserSession(entity))
        }
    }
}