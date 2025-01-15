plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)

}

android {
    namespace = "com.theodo.myweather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.theodo.myweather"
        minSdk = 24
        targetSdk = 34
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
        compose = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Room database
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    annotationProcessor(libs.room.compiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.3.0")

    // Dependency Injection
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Moshi
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")


    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Swiperefresh
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.13-rc")

    implementation ("androidx.compose.material:material-icons-extended:1.5.3")


    // For InstantTaskExecutorRule
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    testImplementation ("org.mockito:mockito-core:5.3.1")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("org.jetbrains.kotlin:kotlin-test:1.8.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation ("org.mockito:mockito-inline:4.3.1")

}