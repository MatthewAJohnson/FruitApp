package com.example.fruitapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fruitapp.R
import com.example.fruitapp.presentation.AnalyticsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var analyticsViewModel: AnalyticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        logExceptions()
    }

    private fun setupViewModel() {
        analyticsViewModel = ViewModelProvider(this).get(AnalyticsViewModel::class.java)
    }

    private fun logExceptions() {
        Thread.setDefaultUncaughtExceptionHandler { _, e ->
            analyticsViewModel.logErrors(e.message)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> true
        else -> super.onOptionsItemSelected(item)
    }
}