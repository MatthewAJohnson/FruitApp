package com.example.fruitapp.remote

import com.example.fruitapp.models.FruitList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("fmtvp/recruit-test-data/master/data.json")
    suspend fun getFruitList(): Response<FruitList>

    @GET("fmtvp/recruit-testdata/master/stats")
    suspend fun getNetworkLoadTime(
        @Query("event") event: String = "load",
        @Query("data") data: String
    ): Response<Void>

    @GET("fmtvp/recruit-testdata/master/stats")
    suspend fun getPageLoadTime(
        @Query("event") event: String = "display",
        @Query("data") data: String
    ): Response<Void>

    @GET("fmtvp/recruit-testdata/master/stats")
    fun getErrorData(
        @Query("event") event: String = "error",
        @Query("data") data: String
    ): Response<Void>
}