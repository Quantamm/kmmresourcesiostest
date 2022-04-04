## Running tests for Android
./gradlew cleanTestDebugUnitTest testDebugUnitTest --tests "com.jeremyfiggins.*" -i

## Running tests for iOS

./gradlew clean generateLocalizations iosTest -i
