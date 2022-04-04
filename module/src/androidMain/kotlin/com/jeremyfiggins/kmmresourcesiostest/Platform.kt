package com.jeremyfiggins.kmmresourcesiostest

import android.content.Context
import com.jeremyfiggins.kmmresourcesiostest.localization.localizationContext

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual fun setResourceLocator(resourceLocator: Any?) {
        (resourceLocator as? Context)?.let { context ->
            localizationContext = context
        }
    }
}