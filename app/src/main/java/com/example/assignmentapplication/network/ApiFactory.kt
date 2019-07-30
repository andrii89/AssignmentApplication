package com.example.assignmentapplication.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private const val SECONDS_OF_TIMEOUT: Long = 30

    private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val gson : Gson = GsonBuilder().setLenient().create()
    private val loggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()
    private val retrofitBuilder : Retrofit.Builder = Retrofit.Builder()

    private val logging = loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    private val apiClient = okHttpClientBuilder
        .connectTimeout(SECONDS_OF_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(SECONDS_OF_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(SECONDS_OF_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(logging).build()

    private fun retrofit(baseURl : String): Retrofit = retrofitBuilder
        .client(apiClient)
        .baseUrl(baseURl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    fun getAPI(baseURl : String) : API {
        return retrofit(baseURl).create(API::class.java)
    }
}