package com.android.kazarovdelivery.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.android.kazarovdelivery.MainActivityViewModel
import com.android.kazarovdelivery.R
import com.android.kazarovdelivery.databinding.FragmentHomeBinding
import com.android.kazarovdelivery.model.FoodCategory
import com.android.kazarovdelivery.model.Data
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class HomeFragment: BaseMvRxFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: MainActivityViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val halfHeight = requireContext().resources.displayMetrics.heightPixels/2

        val slides = mutableListOf(
            CarouselItem(
                imageDrawable = R.drawable.flash_sale
            ),
            CarouselItem(
                imageDrawable = R.drawable.boxing_day_sale
            ),
            CarouselItem(
                imageDrawable = R.drawable.coming_soon
            )
        )
        binding.slider.addData(slides)

        val params = binding.slider.layoutParams
        params.height = halfHeight
        binding.slider.layoutParams = params

        val bottomSheetBehaviour = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehaviour.peekHeight = halfHeight
        bottomSheetBehaviour.addBottomSheetCallback ( object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.bottomSheet.setBackgroundColor(
                            ContextCompat.getColor(requireContext(), android.R.color.white)
                        )
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.bottomSheet.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.bottom_sheet_background
                        )
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
               //DO nothing
            }

        })

        binding.viewPager.adapter = FoodPagerAdapter(
            childFragmentManager,
            listOf(
                GenericFoodFragment(FoodCategory.PIZZA),
                GenericFoodFragment(FoodCategory.SUSHI),
                GenericFoodFragment(FoodCategory.DRINK),
            )
        )
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.cartButton.setOnClickListener {
            viewModel.validateMoveToSummary()
        }

        viewModel.observeMoveToSummaryFragment.observe(viewLifecycleOwner) { data ->
            when (data.status) {
                Data.Status.SUCCESS -> {
                    moveToSummary()
                    viewModel.observeMoveToSummaryFragment.postValue(Data.init())
                }
                Data.Status.ERROR ->
                    Toast.makeText(requireContext(), data.message, Toast.LENGTH_LONG).show()
                else -> { /* DO NOTHING */}
            }
        }
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            val count = state.cart.size
            binding.badgeCount.isVisible = count >= 1
            binding.badgeCount.text = count.toString()
        }
    }

    private fun moveToSummary() {
        try{
            val action = HomeFragmentDirections
                .actionHomeFragmentToSummaryFragment()
            findNavController(binding.root).navigate(action)
        }catch (ex: Exception){
            Toast.makeText(requireContext(), "Oops!!! an error occurred", Toast.LENGTH_LONG).show()
        }
    }
}