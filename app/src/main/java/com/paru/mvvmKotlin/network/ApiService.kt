package com.paru.mvvmKotlin.network

import com.google.gson.GsonBuilder
import com.paru.mvvmkotlin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("login")
    fun login(@Body postParam: HashMap<String, String>): Call<JSONObject>

    companion object RetrofitClient {
        private const val BASE_URL = "http://point-to-your-server/example/paru/"
        fun serviceRequest(): ApiService {
            val headerInterceptor = HeaderInterceptor()
            val client = OkHttpClient().newBuilder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }).build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val deliveryApiService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ApiService::class.java)
            return deliveryApiService
        }
    }
}