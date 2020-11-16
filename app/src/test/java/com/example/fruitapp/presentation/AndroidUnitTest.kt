package com.example.fruitapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Rule

open class AndroidUnitTest {
    @get:Rule
    var instantExecutionRule = InstantTaskExecutorRule()
    fun awaitCoroutineCompletion(viewModel: BaseViewModel) {
        runBlocking {
            viewModel.scope.coroutineContext[Job]!!.children.forEach {
                it.join()
                it.children.forEach { children -> children.join() }
            }
        }
    }
}