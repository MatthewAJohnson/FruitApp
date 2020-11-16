package com.example.fruitapp

import com.example.fruitapp.models.FruitResult
import com.example.fruitapp.models.FruitList
import com.example.fruitapp.remote.ApiService
import com.example.fruitapp.remote.RemoteApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response.success

@ExperimentalCoroutinesApi
class RemoteApiServiceShould {
    private lateinit var remoteApiService: RemoteApiService
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        apiService = mockk()
        remoteApiService = RemoteApiService(apiService)
    }

    @Test
    fun `return an empty list of fruit`() = runBlockingTest {
        val fruit = FruitList(listOf<FruitResult>())
        coEvery { apiService.getFruitList() } returns success(fruit)
        val response = remoteApiService.getFruitList()
        assertThat(response.isRight, equalTo(true))
        assertThat(response.right.count(), equalTo(0))
    }
}