package com.upr.mvvmsample.framework.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.upr.mvvmsample.R
import com.upr.mvvmsample.framework.ui.common.ViewResult
import com.upr.mvvmsample.framework.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private val loginVM: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        initVMEvents()
    }

    private fun initViews() {
        val usernameET = findViewById<EditText>(R.id.et_login_username)
        val passwordET = findViewById<EditText>(R.id.et_login_password)
        findViewById<Button>(R.id.btn_login_auth).setOnClickListener {
            loginVM.loginUserNoCache(usernameET.text.toString(), passwordET.text.toString())
        }
    }

    private fun initVMEvents() {
        loginVM.loginEvent.observe(this, {
            when (it) {
                is ViewResult.Success -> loadHome()
                is ViewResult.Failure -> showError(it.details.message)
            }
        })
    }

    private fun loadHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}