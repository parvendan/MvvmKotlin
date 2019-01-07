package com.paru.mvvmKotlin.ui.activity.loginActivity

import android.arch.lifecycle.LiveData
import com.paru.mvvmKotlin.network.ApiService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.paru.mvvmKotlin.base.App
import com.paru.mvvmKotlin.network.response.LoginResponse
import com.paru.mvvmKotlin.util.Constants
import com.paru.mvvmKotlin.util.PreferenceHelper


class LoginModel {

    fun login(postParam: HashMap<String, String>): LiveData<LoginResponse> {
        val deliveryApiService = ApiService.serviceRequest()
        val data = MutableLiveData<LoginResponse>()
        deliveryApiService.login(postParam).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginFailedResponse(data)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                    val jSession: String = response.headers().get("Set-Cookie")!!
                    val jSessionId = jSession.split(";").toTypedArray()
                    val sessionId = jSessionId.get(0)

                } else {
                    loginFailedResponse(data)
                }

            }
        })
        return data
    }

    fun loginFailedResponse(data: MutableLiveData<LoginResponse>) {
        val loginResponse = LoginResponse()
        loginResponse.status = 1
        data.postValue(loginResponse)
    }
}


