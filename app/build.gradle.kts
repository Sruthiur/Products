plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
//    alias(libs.plugins.android.library)
//    id("org.jetbrains.kotlin.kapt")
//    kotlin("jvm") version "1.9.0" apply false
//    kotlin("kapt") version "1.9.0" apply false
//    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.dagger.hilt.android)
//    alias(libs.plugins.google.gms.google.services)
//    alias(libs.plugins.kotlin.kapt) // Add this line to apply the KAPT plugin
//    alias(libs.plugins.google.dagger.hilt.android)

}
android {
    namespace = "com.products.products"
    compileSdk = 35

    buildToolsVersion = "34.0.0"

    defaultConfig {
        applicationId = "com.products.products"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }

  dataBinding {
      enable = true
  }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.hilt.android)
    implementation(libs.glide)
    kapt(libs.compiler)
    kapt(libs.hilt.compiler)  // Use kapt for Hilt's annotation processor
    kapt(libs.androidx.hilt.compiler)
//    implementation(libs.hilt.android) // Hilt Android
//    kapt(libs.hilt.compiler)  // Use kapt for Hilt's annotation processor
//    kapt(libs.androidx.hilt.compiler) // Optional if you use AndroidX Hilt

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

