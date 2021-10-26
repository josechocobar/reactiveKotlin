package com.chocobar.reactiveKotlin.data.remote

import com.chocobar.reactiveKotlin.data.models.Constants.API_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private val client : OkHttpClient  = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10,TimeUnit.SECONDS)
        .readTimeout(10,TimeUnit.SECONDS)
        .callTimeout(10,TimeUnit.SECONDS)
        .build()

    val webService : WebService by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

}