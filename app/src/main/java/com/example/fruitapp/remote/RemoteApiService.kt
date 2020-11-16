package com.example.fruitapp.remote

import com.example.fruitapp.remote.mappers.fruit


class RemoteApiService(private val apiService: ApiService = RetrofitBuilder().create(ApiService::class.java)) {
    suspend fun getFruitList() = execute { apiService.getFruitList() }.mapTo { it.fruit() }

    suspend fun getNetworkLoadTime(data: String) =
        execute { apiService.getNetworkLoadTime(data = data) }

    suspend fun getPageLoadTime(data: String) = execute { apiService.getPageLoadTime(data = data) }

    suspend fun getErrorData(data: String) = execute { apiService.getErrorData(data = data) }
}