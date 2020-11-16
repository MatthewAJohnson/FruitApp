package com.example.fruitapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fruitapp.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {
    val scope = CoroutineScope(Dispatchers.IO)
    val serverFailure = MutableLiveData<Failure>()

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

    fun handleFailure(): (Failure) -> Unit =
        { serverFailure.postValue(it) }
}
