package com.example.fruitapp.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_fruit.view.*

class FruitViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(fruitName: String) {
        view.fruit_name.text = fruitName
    }
}