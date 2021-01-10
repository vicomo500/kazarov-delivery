package com.android.kazarovdelivery.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.android.kazarovdelivery.MainActivityViewModel
import com.android.kazarovdelivery.R
import com.android.kazarovdelivery.databinding.FragmentCartBinding
import com.android.kazarovdelivery.model.Food

class CartFragment : BaseMvRxFragment(), CartListAdapter.ClickListener {
    private lateinit var binding: FragmentCartBinding

    private val viewModel: MainActivityViewModel by activityViewModel()

    private lateinit var cartListAdapter: CartListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cart, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartListAdapter = CartListAdapter( listener = this)
        binding.recyclerview.adapter = cartListAdapter
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            cartListAdapter.update(state.cart)

            val totalAmt = state.cart.map { it.price }.sum()
            binding.price.text =
                binding.root.context.getString(R.string.amount_in_usd, totalAmt)
        }
    }

    override fun onDelete(item: Food) {
        viewModel.removeFromCart(item)
    }
}