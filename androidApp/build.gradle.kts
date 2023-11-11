plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rosystem.food_app.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "ru.rosystem.food_app.android"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)

    // app libs
    implementation(libs.mvikotlin)
    implementation(libs.mvikotlin.main)
    implementation(libs.mvikotlin.timetravel)
    implementation(libs.mvikotlin.extensions.coroutines)
    implementation(libs.mvikotlin.essentity)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    debugImplementation(libs.compose.ui.tooling)
}