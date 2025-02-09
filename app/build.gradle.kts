
plugins {
    alias(libs.plugins.rafael.application)
    alias(libs.plugins.rafael.application.compose)
}

android {
    namespace = "com.erafael.mercadolivre"

    defaultConfig {
        applicationId = "com.erafael.mercadolivre"
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.core)
            implementation(libs.androidx.activity.compose)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.erafael.mercadolivre"
    generateResClass = always
}
