package com.jeremyfiggins.kmmresourcesiostest

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting(null).contains("Android"))
        assertTrue(Greeting().greeting(null).contains("be OK"))
    }
}
