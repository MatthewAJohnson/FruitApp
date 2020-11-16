package com.example.fruitapp.remote

import com.example.fruitapp.models.FruitResult
import com.example.fruitapp.models.FruitList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response
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

    @Test
    fun `send stats for request load time`() = runBlockingTest {
        coEvery { apiService.getNetworkLoadTime(data = "300", event = "load") } returns success(mockk<Void>())
        val response = remoteApiService.getNetworkLoadTime(data="300")
        assertThat(response.isRight, equalTo(true))
    }

    @Test
    fun `send stats when a page is loaded`() = runBlockingTest {
        coEvery { apiService.getPageLoadTime(data = "200") } returns success(mockk<Void>())
        val response = remoteApiService.getPageLoadTime(data = "200")
        assertThat(response.isRight, equalTo(true))
    }

    @Test
    fun `send stats about exceptions`() = runBlockingTest {
        coEvery { apiService.getErrorData(data = "an error") } returns success(mockk<Void>())
        val response = remoteApiService.getErrorData(data = "an error")
        assertThat(response.isRight, equalTo(true))
    }
}