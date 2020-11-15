package com.example.fruitapp.remote

import com.example.fruitapp.remote.mappers.fruit


class RemoteApiService(private val apiService: ApiService = RetrofitBuilder().create(ApiService::class.java) ) {
    suspend fun getFruitList() = execute {apiService.getFruitList()}.mapTo { it.fruit() }
}