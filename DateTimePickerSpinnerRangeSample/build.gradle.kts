plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    signingConfigs {
        create("release") {
            storeFile = File("..\\signs\\release.jks")
            storePassword = "DTRELEASE"
            keyAlias = "release"
            keyPassword = "DTRELEASE"
        }
    }
    namespace = "com.jr.datetimepickerspinnerrangesample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jr.datetimepickerspinnerrangesample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "enviroment"

    productFlavors {
        create("pro") {
            signingConfig = signingConfigs.getByName("release")
        }

        create("qa") {
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":DateTimePickerSpinnerRangeLibrary"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.android.joda)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}