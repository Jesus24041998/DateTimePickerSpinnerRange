plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.nmcp)
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

    compileSdk = 34
    namespace = "com.jr.datetimepickerspinnerrangelibrary"
    version =  providers.gradleProperty("VERSION_NAME").get()


    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isJniDebuggable = true
        }
    }

    flavorDimensions += "enviroment"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

