// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.Kotlin.gradlePlugin)
        classpath(Libs.Hilt.gradlePlugin)
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            // Use experimental APIs
            freeCompilerArgs = freeCompilerArgs.toMutableList().apply {
                add("-Xopt-in=kotlin.RequiresOptIn")
                add("-Xallow-jvm-ir-dependencies")
                add("-Xskip-prerelease-check")
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}