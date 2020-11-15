package com.example.fruitapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fruitapp.Either
import com.example.fruitapp.models.Fruit
import com.example.fruitapp.models.FruitList
import com.example.fruitapp.remote.RemoteApiService
import io.mockk.coEvery
import io.mockk.mockk
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
class FruitViewModelShould(){

    @get:Rule
    var instantExecutionRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FruitViewModel
    private lateinit var fruitApi : RemoteApiService
    @Before
    fun setup(){
        fruitApi = mockk()
        viewModel = FruitViewModel(fruitApi)

    }

    @Test
    fun `retrieve list of fruit`() = runBlockingTest {
        val fruitList = listOf(Fruit(""))
        coEvery { fruitApi.getFruitList() } returns Either.Right(fruitList)
        viewModel.getFruitList()
        awaitCoroutineCompletion(viewModel)
        assertThat(viewModel.fruitList.value!!, equalTo(fruitList))
    }

    fun awaitCoroutineCompletion(viewModel: FruitViewModel){
        runBlocking {
            viewModel.scope.coroutineContext[Job]!!.children.forEach {
                it.join()
                it.children.forEach { children -> children.join() }
            }
        }
    }
}