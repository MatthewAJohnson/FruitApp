package com.example.fruitapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fruitapp.Failure
import com.example.fruitapp.Providers
import com.example.fruitapp.remote.RemoteApiService
import com.example.fruitapp.models.Fruit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FruitViewModel(private val fruitApi: RemoteApiService = Providers.remoteApiService) :
    ViewModel() {
    val fruitList = MutableLiveData<List<Fruit>>()
    val serverFailure = MutableLiveData<Failure>()
    val scope = CoroutineScope(Dispatchers.IO)

    fun getFruitList() {
        scope.launch {
            fruitApi.getFruitList().either({
                serverFailure.postValue(it)
            }, {
                fruitList.postValue(it)
            })
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}