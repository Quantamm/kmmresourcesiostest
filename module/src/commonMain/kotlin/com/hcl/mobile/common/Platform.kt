package com.jeremyfiggins.kmmresourcesiostest

expect class Platform() {
    val platform: String
    fun setResourceLocator(resourceLocator: Any?)
}