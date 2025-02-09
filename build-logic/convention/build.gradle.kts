import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    `kotlin-dsl`
}

group = "com.rafael"

// Configure the build-logic plugins to target JDK 21
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.composeCompiler.gradle)
    compileOnly(libs.compose.gradle)
}

gradlePlugin {
    // register the convention plugin
    plugins {
        register("spotless") {
            id = "com.rafael.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
        register("library") {
            id = "com.rafael.library"
            implementationClass = "MultiplatformLibraryConventionPlugin"
        }
        register("composeLibrary") {
            id = "com.rafael.compose.library"
            implementationClass = "MultiplatformLibraryComposeConventionPlugin"
        }
        register("application") {
            id = "com.rafael.application"
            implementationClass = "MultiplatformApplicationConventionPlugin"
        }
        register("composeApplication") {
            id = "com.rafael.compose.application"
            implementationClass = "MultiplatformApplicationComposeConventionPlugin"
        }
    }
}

