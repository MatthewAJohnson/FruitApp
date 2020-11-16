package com.example.fruitapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitapp.FruitDetailFragmentArgs
import com.example.fruitapp.R
import com.example.fruitapp.models.Fruit
import kotlinx.android.synthetic.main.viewholder_fruit.view.*

class FruitAdapter(var fruits: List<Fruit>) : RecyclerView.Adapter<FruitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_fruit, parent, false)
        return FruitViewHolder(view).apply {
            itemView.setOnClickListener {
                it.findNavController().navigate(
                    R.id.action_global_fruitDetailFragment,
                    FruitDetailFragmentArgs(fruits[adapterPosition]).toBundle()
                )
            }
        }
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.itemView.fruit_name.text = fruits[position].type
    }

    override fun getItemCount(): Int = fruits.count()

}
