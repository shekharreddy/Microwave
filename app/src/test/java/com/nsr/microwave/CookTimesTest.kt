package com.nsr.microwave

import org.junit.Test

import org.junit.Assert.*
import com.nsr.microwave.utils.CookingTimes.CookingType.POTATO
import com.nsr.microwave.utils.CookingTimes.CookingType.VEGETABLE
import com.nsr.microwave.utils.CookingTimes.CookingType.BEVERAGE

class CookTimesTest {

    @Test
    fun verifyCookTimes() {
        assertEquals(POTATO.cookTime, 25000)
        assertEquals(VEGETABLE.cookTime, 30000)
        assertEquals(BEVERAGE.cookTime, 15000)

    }
}