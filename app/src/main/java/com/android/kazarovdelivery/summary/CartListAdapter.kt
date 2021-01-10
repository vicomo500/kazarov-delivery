package com.android.kazarovdelivery.summary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.kazarovdelivery.R
import com.android.kazarovdelivery.databinding.ListItemCartBinding
import com.android.kazarovdelivery.model.Food

class CartListAdapter(
    private val list: MutableList<Food> = mutableListOf(),
    private val listener: ClickListener
): RecyclerView.Adapter<CartListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_cart,
                parent,
                false
            )
        )
    }

    fun update(cart: List<Food>) {
        list.clear()
        list.addAll(cart)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
    inner class ViewHolder(private val binding: ListItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(food: Food) {
                binding.image.setImageResource(food.image)
                binding.name.text = food.name
                val priceText = binding.root.context.getString(
                    R.string.amount_in_usd, food.price
                )
                binding.price.text = priceText
                binding.delete.setOnClickListener {
                    listener.onDelete(food)
                }
            }
    }

    interface ClickListener {
        fun onDelete(item: Food)
    }
}