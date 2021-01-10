package com.android.kazarovdelivery.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.airbnb.mvrx.*
import com.android.kazarovdelivery.MainActivityViewModel
import com.android.kazarovdelivery.R
import com.android.kazarovdelivery.databinding.FragmentGenericFoodBinding
import com.android.kazarovdelivery.model.Food
import com.android.kazarovdelivery.model.FoodCategory

class GenericFoodFragment(
    val category: FoodCategory
) : BaseMvRxFragment(), FoodListAdapter.ClickListener {

    private lateinit var binding: FragmentGenericFoodBinding

    private val viewModel: MainActivityViewModel by activityViewModel()

    private lateinit var foodAdapter: FoodListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_generic_food, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodAdapter = FoodListAdapter( listener = this)
        binding.recyclerview.adapter = foodAdapter
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            when(state.foodList) {
                is Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                    binding.recyclerview.visibility = View.GONE
                }
                is Success -> {
                    binding.progressbar.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE
                    val list = state.foodList.invoke().filter { it.category == category }
                    foodAdapter.update(list)
                }
                is Fail -> {
                    Toast.makeText(requireContext(), "Failed to load all food list", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }

        }
    }

    override fun onClick(item: Food) {
       viewModel.addToCart(item)
    }
}