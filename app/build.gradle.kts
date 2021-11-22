import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(31)

    defaultConfig {
        applicationId = "com.jhughes.eznews"
        minSdkVersion(31)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String",
            "NEWS_API_KEY",
            "\"${gradleLocalProperties(rootDir).getProperty("NEWS_ORG_API_KEY") as String}\""
        )
    }

    buildTypes {
        getByName("release") {
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
}

dependencies {

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Kotlin.Coroutines.android)

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.activityKtx)
    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.AndroidX.fragmentKtx)
    implementation(Libs.AndroidX.navigationCompose)
    implementation(Libs.AndroidX.hiltNavigationCompose)
    implementation(Libs.AndroidX.dataStore)
    implementation(Libs.AndroidX.Compose.compiler)
    implementation(Libs.AndroidX.Compose.runtime)
    implementation(Libs.AndroidX.Compose.foundation)
    implementation(Libs.AndroidX.Compose.layout)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.UI.tooling)
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.animation)
    implementation(Libs.AndroidX.Compose.materialIconsExtended)
    implementation(Libs.AndroidX.Compose.runtimeLivedata)
    implementation(Libs.AndroidX.Lifecycle.viewModelKtx)
    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(Libs.AndroidX.Paging.runtime)
    implementation(Libs.AndroidX.Paging.compose)

    implementation(Libs.Accompanist.insets)
    implementation(Libs.Accompanist.systemUiController)
    implementation(Libs.Accompanist.flowLayout)
    implementation(Libs.Accompanist.swipeToRefresh)
    implementation(Libs.Accompanist.navigationMaterial)
    implementation(Libs.Accompanist.navigationAnimation)

    implementation(Libs.Coil.coilCompose)

    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.compiler)

    implementation(Libs.Moshi.moshi)
    implementation(Libs.Moshi.kotlin)
    implementation(Libs.Moshi.adapters)
    implementation(Libs.okHttpLogging)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.moshiConverter)

    debugImplementation(Libs.Chucker.chucker)
    releaseImplementation(Libs.Chucker.noOp)
}