package com.android.kazarovdelivery.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.airbnb.mvrx.BaseMvRxFragment
import com.android.kazarovdelivery.R
import com.android.kazarovdelivery.databinding.FragmentSummaryBinding

class SummaryFragment: BaseMvRxFragment() {

    private lateinit var binding: FragmentSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_summary, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tabFragments = listOf(
            CartFragment(),
            Fragment(), // i.e. orders fragment
            Fragment() // i.e. information fragment
        )
        binding.viewPager.adapter =
            object: FragmentStatePagerAdapter(
                childFragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            ) {
                override fun getCount(): Int = tabFragments.size

                override fun getItem(position: Int): Fragment = tabFragments[position]

                override fun getPageTitle(position: Int): CharSequence? =
                    when (position) {
                        0 -> "Cart"
                        1 -> "Orders"
                        2 -> "Information"
                        else -> super.getPageTitle(position)
                    }
            }
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun invalidate() {

    }

}