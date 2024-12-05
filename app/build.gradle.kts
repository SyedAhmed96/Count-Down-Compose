plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.ahmed.countdowncompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ahmed.countdowncompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core Android dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")

    // Compose BOM (Bill of Materials)
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Lifecycle & ViewModel support for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Flow row // for dynamic size row
    implementation ("com.google.accompanist:accompanist-flowlayout:0.30.1")

    // Debugging tools
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Unit testing
    testImplementation("junit:junit:4.13.2")
    // Coroutine testing dependencies
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0") // Adjust the version as needed
    testImplementation ("org.mockito:mockito-core:4.6.1")  // Mockito for mocking dependencies


    // Instrumentation testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.01.00"))

    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0")
    androidTestImplementation ("junit:junit:4.13.2")
}
