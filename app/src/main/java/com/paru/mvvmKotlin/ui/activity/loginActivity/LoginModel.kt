package com.paru.mvvmKotlin.ui.activity.loginActivity

import android.arch.lifecycle.LiveData
import com.paru.mvvmKotlin.network.ApiService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.arch.lifecycle.MutableLiveData


class LoginModel {

    fun login(postParam: HashMap<String, String>): LiveData<JSONObject> {
        val deliveryApiService = ApiService.serviceRequest()
        val data = MutableLiveData<JSONObject>()
        deliveryApiService.login(postParam).enqueue(object : Callback<JSONObject> {
            override fun onFailure(call: Call<JSONObject>, t: Throwable) {

            }

            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                }

            }
        })
        return data
    }
}


