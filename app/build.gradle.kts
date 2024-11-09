plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

}

android {
    namespace = "com.example.weather_compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weather_compose"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.weather_compose.utilities.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "VISUAL_CROSSING_API_KEY", "\"Q7QWWNW2LL5Y6R6X6YEHF939X\"")
        buildConfigField("String", "AMADEUS_CITY_SEARCH_API_KEY", "\"J1dOAV88wjWVdiuQEMWIyfIgQUoyw896\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        // Enable Coroutines API
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }
    buildFeatures {
        compose = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Networking
        //Converter
    implementation(libs.gson)
    implementation(libs.okhttp3.logging.interceptor)
        //Type safe HTTP client
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2)

    //Image Loading
    implementation(libs.coil)
    implementation(libs.coil.compose)

    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.compose)

    //DI
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.play.services.location)


    //Work Manager
    implementation(libs.androidx.work.runtime.ktx)

    //Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    //KotlinX immutable collections
    implementation(libs.kotlinx.collections)

    //Compose Material 3 Design
    implementation(libs.androidx.material3)
    implementation(libs.material)

    //Permissions
    implementation(libs.accompanist.permissions)


    //Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core.testing)
    testImplementation(libs.mockk)
    //androidTestImplementation(libs.androidx.junit)
// debugImplementation(libs.androidx.monitor)

    androidTestImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.test)
    //Turbine for Flow testing
    androidTestImplementation(libs.turbine)

    // Android Architecture Components testing library
    androidTestImplementation(libs.androidx.arch.core.testing)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.intents)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)


//    androidTestImplementation(libs.androidx.test.uiautomator)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //Hilt Android Testing
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)



}

apply(from = "${project.rootDir}/buildscripts/jacoco.gradle")