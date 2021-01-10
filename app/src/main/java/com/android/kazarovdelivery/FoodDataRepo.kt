package com.android.kazarovdelivery

import com.android.kazarovdelivery.model.Food
import com.android.kazarovdelivery.model.FoodCategory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FoodDataRepo {
    fun getFoodData(): Observable<List<Food>> = Observable.fromCallable {
        Thread.sleep(3000)
        listOf(
            Food(
                1,
                FoodCategory.PIZZA,
                R.drawable.pizza,
                "Deluxe",
                listOf("Chicken", "Mozarella", "Pineapple", "Domino Sauce"),
                200,
                35,
                55F
            ),
            Food(
                1,
                FoodCategory.DRINK,
                R.drawable.drink,
                "Lemon Wine",
                listOf("Lemon"),
                10,
                12,
                120F
            ),
            Food(
                1,
                FoodCategory.PIZZA,
                R.drawable.pizza_2,
                "Hawaiian",
                listOf("Chicken", "Mozarella", "Pineapple", "Domino Sauce"),
                200,
                35,
                55F
            ),
            Food(
                1,
                FoodCategory.SUSHI,
                R.drawable.sushi,
                "Japanese Sushi",
                listOf("Fish", "Bread crumbs"),
                21,
                8,
                29.82F
            ),
            Food(
                1,
                FoodCategory.PIZZA,
                R.drawable.pizza,
                "Pepperoni",
                listOf("Pepper", "Salt", "Sugar", "Aska Sauce"),
                590,
                51,
                100F
            ),
            Food(
                1,
                FoodCategory.DRINK,
                R.drawable.drink_2,
                "Champagne",
                listOf("Alcohol"),
                11,
                0,
                2000F
            ),
            Food(
                1,
                FoodCategory.PIZZA,
                R.drawable.pizza_2,
                "California",
                listOf("Barbecue", "Beef", "Rasin", "Flour"),
                110,
                30,
                29.01F
            ),
            Food(
                1,
                FoodCategory.PIZZA,
                R.drawable.pizza,
                "The egoist",
                listOf("Chicken", "Mozarella", "Pineapple", "Domino Sauce"),
                200,
                35,
                55F
            ),
            Food(
                1,
                FoodCategory.DRINK,
                R.drawable.drink,
                "Spanish Arc",
                listOf("Alcohol"),
                21,
                11,
                19.1F
            ),
            Food(
                1,
                FoodCategory.DRINK,
                R.drawable.drink_2,
                "Mazarella",
                listOf("Milk", "Glucose"),
                0,
                1,
                5.0F
            ),
            Food(
                1,
                FoodCategory.SUSHI,
                R.drawable.sushi_2,
                "Taenko Dish Spur",
                listOf("Blacier", "Chinese rib", "Fish"),
                1,
                1,
                5.0F
            ),
            Food(
                1,
                FoodCategory.DRINK,
                R.drawable.drink,
                "Amarujala",
                listOf("Alcoholic"),
                23,
                0,
                77F
            ),
            Food(
                1,
                FoodCategory.SUSHI,
                R.drawable.sushi,
                "Samayena",
                listOf("Nasae"),
                1,
                0,
                11.0F
            ),
            Food(
                1,
                FoodCategory.DRINK,
                R.drawable.drink_2,
                "Tate Spirit",
                listOf("Sugar", "Alcohol", "Milk"),
                11,
                9,
                10F
            ),
            Food(
                1,
                FoodCategory.SUSHI,
                R.drawable.sushi_2,
                "America Sushi",
                listOf(),
                10,
                11,
                65.10F
            ),

        )
    }.subscribeOn(Schedulers.io())

}
