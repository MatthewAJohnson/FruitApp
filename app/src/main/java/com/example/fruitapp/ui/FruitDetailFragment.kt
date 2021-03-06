package com.example.fruitapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fruitapp.R
import kotlinx.android.synthetic.main.fragment_fruit_detail.*

class FruitDetailFragment : BaseFragment() {

    private val args: FruitDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fruit_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFruitValues()
        backButtonListener()
    }

    private fun setFruitValues() {
        args.fruit?.let {
            fruit_value.text = it.type
            price_value.text = it.price
            weight_value.text = it.weight
        }
    }

    private fun backButtonListener() {
        back_button.setOnClickListener { findNavController().popBackStack() }
    }
}