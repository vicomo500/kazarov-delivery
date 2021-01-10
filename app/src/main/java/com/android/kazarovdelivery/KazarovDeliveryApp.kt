package com.android.kazarovdelivery

import android.app.Application

class KazarovDeliveryApp: Application() {
    val foodDataRepo by lazy { FoodDataRepo() }
}