plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt.plugin)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.secrets.gradle.plugin)
}

android {
    namespace = "com.test.moviesapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.test.moviesapp"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }
}

detekt {
    toolVersion = libs.versions.detektVersion.get()
    config.setFrom(file("../config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    autoCorrect = true
}

secrets {
    defaultPropertiesFileName = "secrets.properties"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.bundles.networking)
}
