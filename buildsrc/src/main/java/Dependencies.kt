object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.3"

    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:4.9.0"

    object Kotlin {
        const val version = "1.6.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.6.0"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val activityKtx = "androidx.activity:activity-ktx:1.4.0"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.4.1"
        const val navigationCompose = "androidx.navigation:navigation-compose:2.4.0"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0"

        const val junit = "junit:junit:4.13"

        object Compose {
            const val version = "1.1.0-rc03"

            const val compiler = "androidx.compose.compiler:compiler:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"

            object UI {
                const val tooling = "androidx.compose.ui:ui-tooling:$version"
                const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            }
        }

        object Lifecycle {
            private const val version = "2.4.0"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }

        object Paging {
            private const val version = "3.1.0"
            const val runtime = "androidx.paging:paging-runtime:$version"
            const val compose = "androidx.paging:paging-compose:1.0.0-alpha14"
            //without Android dependencies for use tests
            const val common = "androidx.paging:paging-common:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"
            const val runner = "androidx.test:runner:$version"

            object Ext {
                private const val version = "1.1.2-rc01"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Hilt {
        private const val version = "2.39.1"

        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"

    }

    object Accompanist {
        private const val version = "0.22.1-rc"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val flowLayout = "com.google.accompanist:accompanist-flowlayout:$version"
        const val swipeToRefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
        const val navigationMaterial = "com.google.accompanist:accompanist-navigation-material:$version"
        const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:$version"
        const val pager = "com.google.accompanist:accompanist-pager:$version"
        const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$version"
    }

    object Moshi {
        private const val version = "1.11.0"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val adapters = "com.squareup.moshi:moshi-adapters:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object Chucker {
        private const val version = "3.5.2"
        const val chucker = "com.github.chuckerteam.chucker:library:$version"
        const val noOp = "com.github.chuckerteam.chucker:library-no-op:$version"
    }

    const val mockk = "io.mockk:mockk:1.10.0"

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.4.0"
    }
}