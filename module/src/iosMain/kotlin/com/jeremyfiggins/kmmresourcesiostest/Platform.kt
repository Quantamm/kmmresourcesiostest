package com.jeremyfiggins.kmmresourcesiostest

import platform.UIKit.UIDevice
import platform.Foundation.NSBundle
import com.jeremyfiggins.kmmresourcesiostest.localization.localizationBundle

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual fun setResourceLocator(resourceLocator: Any?) {
        (resourceLocator as? NSBundle)?.let { bundle ->
            localizationBundle = bundle
        }
    }

}