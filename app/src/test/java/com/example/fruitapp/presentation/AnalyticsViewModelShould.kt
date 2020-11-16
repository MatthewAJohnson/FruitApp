package com.example.fruitapp.presentation

import com.example.fruitapp.Either
import com.example.fruitapp.Failure
import com.example.fruitapp.remote.RemoteApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AnalyticsViewModelShould : AndroidUnitTest() {
    private lateinit var viewModel: AnalyticsViewModel
    private lateinit var fruitApi: RemoteApiService

    @Before
    fun setup() {
        fruitApi = mockk()
        viewModel = AnalyticsViewModel(fruitApi)
    }

    @Test
    fun `post server failure when there is an error`() = runBlockingTest {
        coEvery { fruitApi.getPageLoadTime(any()) } returns Either.Left(Failure.ServerFailure)
        viewModel.logLoadTime(300)
        awaitCoroutineCompletion(viewModel)
        assertThat(viewModel.serverFailure.value!!, IsEqual.equalTo(Failure.ServerFailure))
    }
}