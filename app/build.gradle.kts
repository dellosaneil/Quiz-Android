plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.google.services)
    id("com.google.firebase.crashlytics")
    kotlin("kapt")
}


val versionMajor = 1
val versionMinor = 0
val versionPatch = 0

fun versionNameGradle(): String {
    return "${versionMajor}.${versionMinor}.${versionPatch}"
}

fun versionCodeGradle(): Int {
    return versionMajor * 10000 + versionMinor * 100 + versionPatch
}


android {
    namespace = "com.thelazybattley.joserizalquiz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.thelazybattley.joserizalquiz"
        minSdk = 26
        targetSdk = 34
        versionCode = versionCodeGradle()
        versionName = versionNameGradle()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    packaging {
        resources.merges.add("/META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":common"))
    implementation(project(":feature:quiz"))
    implementation(project(":feature:dashboard"))
    implementation(project(":feature:admin"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)
    testImplementation(libs.junit)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.timber)
    implementation(libs.gson)
    implementation(platform(libs.firestore.bom))
    implementation(libs.app.update)
    implementation(libs.bundles.firebase)
    implementation(libs.app.update.ktx)

}

kapt {
    correctErrorTypes = true
}
