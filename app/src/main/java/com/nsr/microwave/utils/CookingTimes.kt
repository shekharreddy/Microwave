package com.nsr.microwave.utils

/**
 * Cooking times for different type of food
 */
object CookingTimes {

    enum class CookingType(val cookTime: Long) {
        POPCORN(35000),
        POTATO(25000),
        VEGETABLE(30000),
        BEVERAGE(15000),
        MYPLATE(32000),
        REHEAT(15000),
        DEFROST(40000)
    }
}