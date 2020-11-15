package com.example.fruitapp.remote

import com.example.fruitapp.models.FruitList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("fmtvp/recruit-test-data/master/data.json")
    suspend fun getFruitList() : Response<FruitList>
}