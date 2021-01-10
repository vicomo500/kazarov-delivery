package com.android.kazarovdelivery

import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.android.kazarovdelivery.model.Food
import com.android.kazarovdelivery.model.Data

class MainActivityViewModel(
    initialState: FoodDataState,
    repository: FoodDataRepo
): BaseMvRxViewModel<FoodDataState>(initialState, debugMode = true) {

    val observeMoveToSummaryFragment = MutableLiveData<Data<Unit>>(Data.init())

    init {
        setState {
            copy(foodList = Loading())
        }
        repository.getFoodData()
            .execute {
                copy(foodList = it)
            }
    }

    fun addToCart(item: Food) {
        withState { state ->
            val cart = ArrayList(state.cart)
            cart.add(item)
            setState {
                copy(cart = cart )
            }
        }
    }

    fun removeFromCart(item: Food) {
        withState { state ->
            val cart = ArrayList(state.cart)
            cart.remove(item)
            setState {
                copy(cart = cart )
            }
        }
    }

    fun validateMoveToSummary() {
        withState { state ->
            if(state.cart.isNotEmpty()) {
                observeMoveToSummaryFragment.postValue(Data.success(Unit))
            } else {
                observeMoveToSummaryFragment.postValue(Data.error( message = "Your cart is empty"))
            }
        }
    }

    companion object : MvRxViewModelFactory<MainActivityViewModel, FoodDataState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: FoodDataState
        ): MainActivityViewModel {
            val repo = viewModelContext.app<KazarovDeliveryApp>().foodDataRepo
            return MainActivityViewModel(state, repo)
        }
    }
}