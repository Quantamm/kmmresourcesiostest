package com.jeremyfiggins.kmmresourcesiostest

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting(null).contains("iOS"))
        assertTrue(Greeting().greeting(null).contains("be OK"))
    }
}
