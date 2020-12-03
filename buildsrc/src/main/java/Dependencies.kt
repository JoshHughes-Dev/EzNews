object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.2.0-beta01"

    const val junit = "junit:junit:4.13"

    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:4.9.0"

    object Kotlin {
        const val version = "1.4.20"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.4.0"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0-rc01"
        const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha01"
        const val activityKtx = "androidx.activity:activity-ktx:1.2.0-beta01"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.0-beta01"

        const val junit = "junit:junit:4.13"

        const val material = "com.google.android.material:material:1.2.1"

        object Compose {
            const val version = "1.0.0-alpha08"

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
            const val test = "androidx.compose.test:test-core:$version"

            object UI {
                const val tooling = "androidx.ui:ui-tooling:1.0.0-alpha07"
                const val uiTest = "androidx.ui:ui-test:1.0.0-alpha07"
            }
        }

        object Lifecycle {
            private const val version = "2.3.0-beta01"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Paging {
            private const val version = "3.0.0-alpha10"
            const val runtime = "androidx.paging:paging-runtime:$version"
            const val compose = "androidx.paging:paging-compose:1.0.0-alpha03"
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
        private const val version = "2.29.1-alpha"

        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"

        //intergration with Hilt and Jetpack (ie viewModels)
        object AndroidX {
            private const val version = "1.0.0-alpha02"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
            const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
        }
    }

    object Accompanist {
        private const val version = "0.4.0"
        const val coil = "dev.chrisbanes.accompanist:accompanist-coil:$version"
        const val insets = "dev.chrisbanes.accompanist:accompanist-insets:$version"
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
        private const val version = "3.4.0"
        const val chucker = "com.github.chuckerteam.chucker:library:$version"
        const val noOp = "com.github.chuckerteam.chucker:library-no-op:$version"
    }

    const val mockk = "io.mockk:mockk:1.10.0"
}