package com.upr.mvvmsample.framework.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upr.mvvmsample.business.data.RepoResult
import com.upr.mvvmsample.business.data.user.IAuthRepo
import com.upr.mvvmsample.business.domain.feature.user.validation.*
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
    // DI to unit test these - need to handle rx schedulers correctly for unit testing as well
    private val userValidation: IUserDataValidation = UserDataValidation()
    private val authRepo: IAuthRepo = AuthRepo(
        AuthRemote(),
        AuthLocal()
    )

    private val _loginEvent: MutableLiveData<ViewResult<Any>> = MutableLiveData()
    val loginEvent: LiveData<ViewResult<Any>> get() = _loginEvent

    fun loginUser(username: String, password: String) {
        when (val result = runFieldValidation(username, password)) {
            is UserValidationResult.Valid -> {}
            is UserValidationResult.Invalid -> {
                publishError(Throwable(result.reason))
                return
            }
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

    private fun runFieldValidation(username: String, password: String): UserValidationResult {
        return userValidation.validateUserFields(
            UsernameValidationRule(username),
            UserPasswordValidationRule(password)
        )
    }

    private fun publishError(throwable: Throwable) {
        _loginEvent.postValue(
            ViewResult.Failure(ViewResult.FailDetails("Alert!", throwable.message))
        )
    }
}