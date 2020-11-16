package com.example.fruitapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fruitapp.R
import com.example.fruitapp.models.Fruit
import com.example.fruitapp.presentation.FruitViewModel
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
        fruitViewModel.getFruitList()
        setupObservers()
        fruit_recycler.adapter = adapter
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
    }

    private fun handleServerFailure() {
        Toast.makeText(context, "Server failure", Toast.LENGTH_SHORT).show()
    }
}