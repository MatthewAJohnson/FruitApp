package com.example.fruitapp


class RemoteApiService(private val apiService: ApiService = RetrofitBuilder().create(ApiService::class.java,"") ) {
    suspend fun getFruitList() = execute {apiService.getFruitList()}
}