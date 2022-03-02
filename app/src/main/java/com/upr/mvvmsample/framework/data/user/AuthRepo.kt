package com.upr.mvvmsample.framework.data.user

import com.upr.mvvmsample.business.data.RepoResult
import com.upr.mvvmsample.business.data.user.AuthAdapter
import com.upr.mvvmsample.business.data.user.IAuthRepo
import com.upr.mvvmsample.business.data.user.local.IAuthLocal
import com.upr.mvvmsample.business.data.user.remote.IAuthRemote
import com.upr.mvvmsample.business.domain.feature.user.model.UserSession
import io.reactivex.rxjava3.core.Observable

/**
 * Responsibility: Manage data access interaction points for the user authentication process flow
 * Reason to change: Remote/local data access rules needs to change
 */
class AuthRepo(
    private val remoteApi: IAuthRemote,
    private val authPersistence: IAuthLocal
) : IAuthRepo {
    private val authAdapter = AuthAdapter()

    override fun authUser(username: String, password: String): RepoResult<UserSession> {
        return try {
            val rsp = remoteApi.authenticateUser(username, password)
            authPersistence.saveUserSession(authAdapter.toEntity(rsp))
            RepoResult.Success(authAdapter.toModel(rsp))
        } catch (ex: Exception) {
            RepoResult.Error(ex)
        }
    }

    override fun authUserObs(
        username: String,
        password: String
    ): Observable<RepoResult<UserSession>> {
        return Observable.defer {
            return@defer Observable.just(authUser(username, password))
        }
    }
}
