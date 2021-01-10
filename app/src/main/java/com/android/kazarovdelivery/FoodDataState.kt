package com.android.kazarovdelivery

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.android.kazarovdelivery.model.Food

data class FoodDataState (
    val foodList: Async<List<Food>> = Uninitialized,
    val cart: List<Food> = listOf()
): MvRxState