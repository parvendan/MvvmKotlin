package com.paru.mvvmKotlin.network

import okhttp3.Interceptor
import okhttp3.Response
import com.paru.mvvmKotlin.util.Fields
import okhttp3.Request


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest: Request
        // val accessToken = "Bearer " + PreferencesHelper.getPrefRef().getString(PreferencesHelper.ACCESS_TOKEN, "")
        modifiedRequest = originalRequest.newBuilder()
            .header(Fields.CONTENT_TYPE, "application/json")
            .header(Fields.ACCEPT, "application/json")
            //.header(Fields.VERSION_CODE, BuildConfig.VERSION_CODE.toString() + "")
            //.header(Fields.AUTHORIZATION, "")
            .build()
        return chain.proceed(modifiedRequest)
    }
}