package com.upr.mvvmsample.business.data.user

import com.upr.mvvmsample.business.data.RepoResult
import com.upr.mvvmsample.business.data.user.local.AuthLocal
import com.upr.mvvmsample.business.data.user.remote.AuthRemote
import com.upr.mvvmsample.business.domain.feature.user.model.UserSession
import io.reactivex.rxjava3.core.Observable

class AuthRepo {
    private val remoteApi = AuthRemote()
    private val authPersistence = AuthLocal()
    private val authAdapter = AuthAdapter()

    fun authUser(username: String, password: String): RepoResult<UserSession> {
        return try {
            val rsp = remoteApi.authenticateUser(username, password)
            authPersistence.saveUserSession(authAdapter.toEntity(rsp))
            RepoResult.Success(authAdapter.toModel(rsp))
        } catch (ex: Exception) {
            RepoResult.Error(ex)
        }
    }

    fun authUserObs(username: String, password: String): Observable<RepoResult<UserSession>> {
        return Observable.defer {
            return@defer Observable.just(authUser(username, password))
        }
    }
}