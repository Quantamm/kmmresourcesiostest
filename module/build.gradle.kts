import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget


plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dev.jamiecraane.plugins.kmmresources") version "1.0.0-alpha09"
}

kmmResourcesConfig {
    androidApplicationId.set("com.jeremyfiggins.kmmresourcesiostest")
    packageName.set("com.jeremyfiggins.kmmresourcesiostest.localization")
    defaultLanguage.set("en")
    input.set(File(project.projectDir.path, "strings.yaml"))
    output.set(project.projectDir)
    androidStringsPrefix.set("")
    useDefaultTranslationIfNotInitialized.set(true)
}

val plutil = tasks["executePlutil"] // This one is only needed for iOS

val generateLocalizations = tasks["generateLocalizations"]
tasks["preBuild"].dependsOn(generateLocalizations)

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "MobileCommon"
                freeCompilerArgs += listOf("-Xoverride-konan-properties=osVersionMin.ios_arm32=12.0;osVersionMin.ios_arm64=12.0;osVersionMin.ios_x64=12.0")
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }

        }
        val androidMain by getting {
            dependencies {
                val coreKtxVersion = project.properties["coreKtxVersion"]
                implementation("androidx.core:core-ktx:${coreKtxVersion}")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)
    val targetDir = File(buildDir, "xcode-frameworks")

    group = "build"
    dependsOn(generateLocalizations)
    dependsOn(framework.linkTask)
    inputs.property("mode", mode)

    from({ framework.outputDirectory })
    into(targetDir)

    doLast {
        copy {
            from("${project.rootDir}/module/src/commonMain/resources/ios")
            into("${targetDir}/MobileCommon.framework")
        }
    }
} 

tasks.getByName("build").dependsOn(packForXcode)
