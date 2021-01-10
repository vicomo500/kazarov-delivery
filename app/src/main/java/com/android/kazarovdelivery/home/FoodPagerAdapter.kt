package com.android.kazarovdelivery.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class FoodPagerAdapter (
    fragmentManager: FragmentManager,
    private val list: List<GenericFoodFragment>
):
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Fragment = list[position]

    override fun getPageTitle(position: Int): CharSequence =
        list[position].category.name
            .toLowerCase(Locale.getDefault())
            .capitalize(Locale.getDefault())
}