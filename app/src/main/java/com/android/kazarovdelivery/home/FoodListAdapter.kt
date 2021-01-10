package com.android.kazarovdelivery.home

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.kazarovdelivery.R
import com.android.kazarovdelivery.databinding.ListItemFoodBinding
import com.android.kazarovdelivery.model.Food

class FoodListAdapter(
    private val list: MutableList<Food> = mutableListOf(),
    private val listener: ClickListener
): RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    private val handler =  Handler(Looper.getMainLooper())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_food,
                parent,
                false
            )
        )
    }

    fun update(food: List<Food>) {
        list.clear()
        list.addAll(food)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
    inner class ViewHolder(private val binding: ListItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(food: Food) {
                binding.image.setImageResource(food.image)
                binding.name.text = food.name
                binding.ingredients.text = food.ingredients.joinToString ()
                binding.dimen.text =
                    binding.root.context.getString(
                        R.string.dimen,
                        food.weight,
                        food.size
                    )
                val priceText = binding.root.context.getString(
                    R.string.amount_in_usd, food.price
                )
                binding.price.text = priceText
                binding.price.setOnClickListener {
                    listener.onClick(food)
                   binding.price.text = it.context.getString(R.string.added)
                    binding.price.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.price_button_clicked_background
                    )
                    handler.postDelayed({
                        binding.price.text = priceText
                        binding.price.background = ContextCompat.getDrawable(
                            binding.root.context,
                            R.drawable.price_button_background
                        )
                    }, CLICK_CHANGE_DELAY)
                }
            }
    }

    interface ClickListener {
        fun onClick(item: Food)
    }

    companion object {
        private const val CLICK_CHANGE_DELAY = 1000L
    }
}