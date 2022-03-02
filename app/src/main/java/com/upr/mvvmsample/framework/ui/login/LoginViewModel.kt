package com.upr.mvvmsample.framework.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upr.mvvmsample.business.data.RepoResult
import com.upr.mvvmsample.business.data.user.IAuthRepo
import com.upr.mvvmsample.business.domain.feature.user.UserDataValidation
import com.upr.mvvmsample.framework.data.user.AuthRepo
import com.upr.mvvmsample.framework.data.user.local.AuthLocal
import com.upr.mvvmsample.framework.data.user.remote.AuthRemote
import com.upr.mvvmsample.framework.ui.common.ViewResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Responsibility: facilitate use case flow for a login event.
 * Reason to change: authentication process of a user needs to change
 * Use case requirements:
 *      - local input validation on credentials
 *      - request server to for authenticated user session token
 *      - cache session token
 *      - display any network or authentication error to user
 *      - post success when session token is pulled and cached successfully
 */
class LoginViewModel : ViewModel() {
    private val _loginEvent: MutableLiveData<ViewResult<Any>> = MutableLiveData()

    private val authRepo: IAuthRepo = AuthRepo(
        AuthRemote(),
        AuthLocal()
    )

    val loginEvent: LiveData<ViewResult<Any>> get() = _loginEvent

    fun loginUser(username: String, password: String) {
        if (invalidCredentials(username, password)) {
            return
        }

        authRepo.authUserObs(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when (it) {
                    is RepoResult.Success -> _loginEvent.postValue(ViewResult.Success(true))
                    is RepoResult.Error -> publishError(it.throwable)
                }
            }, {
                publishError(it)
            })
    }

    private fun invalidCredentials(username: String, password: String): Boolean {
        return try {
            UserDataValidation.isValidUsername(username)
            UserDataValidation.isValidPassword(password)
            false
        } catch (ex: Exception) {
            publishError(ex)

            true
        }
    }

    private fun publishError(throwable: Throwable) {
        _loginEvent.postValue(
            ViewResult.Failure(ViewResult.FailDetails("Alert!", throwable.message))
        )
    }
}