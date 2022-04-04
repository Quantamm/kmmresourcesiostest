package com.jeremyfiggins.kmmresourcesiostest

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting(null).contains("Hello"))
        assertTrue(Greeting().greeting(null).contains("be OK"))
    }
}
