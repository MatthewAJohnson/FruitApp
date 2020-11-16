package com.example.fruitapp.presentation

import androidx.lifecycle.MutableLiveData
import com.example.fruitapp.Failure
import com.example.fruitapp.Providers
import com.example.fruitapp.models.Fruit
import com.example.fruitapp.remote.RemoteApiService
import kotlinx.coroutines.launch

class FruitViewModel(private val fruitApi: RemoteApiService = Providers.remoteApiService) :
    BaseViewModel() {
    val fruitList = MutableLiveData<List<Fruit>>()

    fun getFruitList() {
        scope.launch {
            val startTime = System.currentTimeMillis()
            fruitApi.getFruitList().either(handleFailure(), handleFruitList())
            fruitApi.getNetworkLoadTime((System.currentTimeMillis() - startTime).toString())
        }
    }

    private fun handleFruitList(): (List<Fruit>) -> Unit =
        { fruitList.postValue(it) }
}