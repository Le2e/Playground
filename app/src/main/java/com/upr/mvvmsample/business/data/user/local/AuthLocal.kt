package com.upr.mvvmsample.business.data.user.local

import com.upr.mvvmsample.business.data.RepoResult
import io.reactivex.rxjava3.core.Observable

class AuthLocal {
    fun saveUserSession(entity: AuthEntity): RepoResult<Boolean> {
        // todo - actually implement persistent storage
        return RepoResult.Success(true)
    }

    fun saveUserSessionObs(entity: AuthEntity): Observable<RepoResult<Boolean>> {
        return Observable.defer {
            return@defer Observable.just(saveUserSession(entity))
        }
    }
}