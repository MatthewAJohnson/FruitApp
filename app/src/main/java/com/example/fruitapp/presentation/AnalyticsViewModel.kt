package com.example.fruitapp.presentation

import com.example.fruitapp.Providers
import com.example.fruitapp.remote.RemoteApiService
import kotlinx.coroutines.launch

class AnalyticsViewModel(private val fruitApi: RemoteApiService = Providers.remoteApiService) :
    BaseViewModel() {

    fun logLoadTime(loadTime: Long) {
        scope.launch {
            fruitApi.getPageLoadTime(loadTime.toString())
                .either({ serverFailure.postValue(it) }, {})
        }
    }

    fun logErrors(message: String?) {
        scope.launch {
            fruitApi.getErrorData(message ?: "there was an error")
        }
    }

}