package com.example.fruitapp

import android.app.Application
import android.content.Context
import com.example.fruitapp.remote.RemoteApiService

class FruitApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyInjector().inject()
    }
}

class DependencyInjector() {
    fun inject() {
        Providers.remoteApiService = RemoteApiService()
    }
}

object Providers {
    lateinit var remoteApiService: RemoteApiService
}
