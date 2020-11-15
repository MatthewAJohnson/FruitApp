package com.example.fruitapp

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    fun <T> create(service: Class<T>, baseUrl: String): T = getRetrofit(baseUrl).create(service)

    private fun getRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder().client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(baseUrl).build()

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        return httpClient.build()
    }

}
