package com.paru.mvvmKotlin.ui.activity.loginActivity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import com.paru.mvvmKotlin.network.response.LoginResponse
import org.json.JSONObject


class LoginViewModel : ViewModel() {

    fun requestLogin(postParam: HashMap<String, String>): LiveData<LoginResponse> {
        val service = LoginModel()
        val user: LiveData<LoginResponse>
        user = service.login(postParam)
        return user
    }

    fun isValidLogin(email: String, password: String): Boolean {
        if (isValidEmail(email) && password.isNotEmpty()) {
            return true
        }
        return false
    }

    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}

