package com.example.fruitapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fruitapp.Either
import com.example.fruitapp.Failure
import com.example.fruitapp.Failure.ServerFailure
import com.example.fruitapp.remote.RemoteApiService
import com.example.fruitapp.models.Fruit
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FruitViewModelShould : AndroidUnitTest() {

    private lateinit var viewModel: FruitViewModel
    private lateinit var fruitApi: RemoteApiService

    @Before
    fun setup() {
        fruitApi = mockk()
        viewModel = FruitViewModel(fruitApi)
    }

    @Test
    fun `retrieve list of fruit`() = runBlockingTest {
        val fruitList = listOf(Fruit("", "", ""))
        coEvery { fruitApi.getFruitList() } returns Either.Right(fruitList)
        viewModel.getFruitList()
        awaitCoroutineCompletion(viewModel)
        assertThat(viewModel.fruitList.value!!.count(), equalTo(1))
    }

    @Test
    fun `post server failure when there is an error`() = runBlockingTest {
        coEvery { fruitApi.getFruitList() } returns Either.Left(ServerFailure)
        viewModel.getFruitList()
        awaitCoroutineCompletion(viewModel)
        assertThat(viewModel.serverFailure.value!!, equalTo(ServerFailure))
    }
}