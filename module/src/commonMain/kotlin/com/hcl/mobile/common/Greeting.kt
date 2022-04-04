package com.jeremyfiggins.kmmresourcesiostest

import com.jeremyfiggins.kmmresourcesiostest.localization.L
import com.jeremyfiggins.kmmresourcesiostest.localization.ok

class Greeting {
    fun greeting(context: Any?): String {
        Platform().setResourceLocator(context)
        return "Hello, ${Platform().platform}! It's all going to be " + L.general.button.ok()
    }
}
