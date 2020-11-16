package com.example.fruitapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fruitapp.R
import com.example.fruitapp.presentation.FruitViewModel
import com.example.fruitapp.models.Fruit
import kotlinx.android.synthetic.main.fragment_fruit_list.*


class FruitListFragment : Fragment() {

    private lateinit var fruitViewModel: FruitViewModel
    private var adapter = FruitAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fruit_list, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fruitViewModel = ViewModelProvider(this).get(FruitViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fruit_recycler.adapter = adapter
        fruitViewModel.getFruitList()
        setupObservers()
        refreshFruitListerner()
    }

    private fun setupObservers() {
        fruitViewModel.fruitList.observe(viewLifecycleOwner, {
            updateFruitList(it)
        })
        fruitViewModel.serverFailure.observe(
            viewLifecycleOwner,
            { handleServerFailure() })
    }

    private fun updateFruitList(it: List<Fruit>) {
        adapter.fruits = it
        adapter.notifyDataSetChanged()
        fruit_recycler.visibility = VISIBLE
    }

    private fun handleServerFailure() {
        Toast.makeText(context, getString(R.string.server_failure), Toast.LENGTH_SHORT).show()
    }

    private fun refreshFruitListerner() {
        refresh_button.setOnClickListener {
            fruitViewModel.getFruitList()
            Toast.makeText(context, getString(R.string.fruit_refreshed), Toast.LENGTH_SHORT).show()
            fruit_recycler.visibility = INVISIBLE
        }
    }
}