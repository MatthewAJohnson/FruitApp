package com.example.fruitapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fruitapp.presentation.AnalyticsViewModel

open class BaseFragment :Fragment() {
    private var startLoading = System.currentTimeMillis()
    private lateinit var analyticsViewModel: AnalyticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analyticsViewModel = ViewModelProvider(this).get(AnalyticsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logLoadTime()
    }

    private fun logLoadTime() {
        analyticsViewModel.logLoadTime(System.currentTimeMillis() - startLoading)
    }
}