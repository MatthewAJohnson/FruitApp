package com.example.fruitapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fruitapp.models.Fruit
import com.example.fruitapp.models.FruitList
import com.example.fruitapp.remote.RemoteApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.security.Provider

class FruitViewModel(private val fruitApi: RemoteApiService) :ViewModel() {
    val fruitList = MutableLiveData<List<Fruit>>()
    val scope = CoroutineScope(Dispatchers.IO)
    fun getFruitList() {
        scope.launch {
            fruitApi.getFruitList().either({  }, {
                fruitList.postValue(it)
            })
        }
    }



    override fun onCleared(){
        super.onCleared()
        scope.cancel()
    }



}