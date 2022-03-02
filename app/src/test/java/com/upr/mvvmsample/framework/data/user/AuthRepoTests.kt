package com.upr.mvvmsample.framework.data.user

import com.upr.mvvmsample.business.data.RepoResult
import com.upr.mvvmsample.business.data.user.local.AuthEntity
import com.upr.mvvmsample.business.data.user.local.IAuthLocal
import com.upr.mvvmsample.business.data.user.remote.IAuthRemote
import com.upr.mvvmsample.business.data.user.remote.UserAuthRsp
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class AuthRepoTests {
    @Test
    fun authUser_FakeRemoteError_ReturnRepoResultError() {
        val mockRemote = object : IAuthRemote {
            override fun authenticateUser(username: String, password: String): UserAuthRsp {
                throw Exception("Remote Server Mock Failure")
            }
        }

        val mockLocal = object : IAuthLocal {
            override fun saveUserSession(entity: AuthEntity): RepoResult<Boolean> {
                TODO("Not yet implemented")
            }

            override fun saveUserSessionObs(entity: AuthEntity): Observable<RepoResult<Boolean>> {
                TODO("Not yet implemented")
            }

        }

        val sut = AuthRepo(mockRemote, mockLocal)

        val result = sut.authUser("", "")

        assert(result is RepoResult.Error)
        // todo - can add mockito to ensure things are called once, never called etc
        // can enure error message matches, etc
    }

    @Test
    fun doTest() {
        assert(true)
    }
}