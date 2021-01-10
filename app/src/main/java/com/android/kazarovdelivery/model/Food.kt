package com.android.kazarovdelivery.model

import androidx.annotation.DrawableRes

data class Food (
    val id: Long,
    val category: FoodCategory,
    @DrawableRes
    val image: Int,
    val name: String,
    val ingredients: List<String>,
    val weight: Int,
    val size: Int,
    val price: Float
    )