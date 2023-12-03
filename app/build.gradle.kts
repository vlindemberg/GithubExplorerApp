plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.navigation)
    alias(libs.plugins.jetbrainsKotlinKapt)
}

android {
    namespace = "com.vinicius.githubexplorerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vinicius.githubexplorerapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        android.buildFeatures.buildConfig = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            versionNameSuffix = "-debug"
            buildConfigField("String", "BASE_URL", project.findProperty("GITHUB_BASE_URL").toString())
            buildConfigField("String", "AUTH_URL", project.findProperty("GITHUB_AUTH_URL").toString())
            buildConfigField("String", "TOKEN_URL", project.findProperty("GITHUB_TOKEN_URL").toString())
            buildConfigField("String", "CLIENT_ID", project.findProperty("GITHUB_CLIENT_ID").toString())
            buildConfigField("String", "CLIENT_SECRET", project.findProperty("GITHUB_CLIENT_SECRET").toString())
            buildConfigField("String", "REDIRECT_URI", project.findProperty("GITHUB_REDIRECT_URI").toString())
            buildConfigField("String", "SCOPE", project.findProperty("GITHUB_SCOPE").toString())
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", project.findProperty("GITHUB_BASE_URL").toString())
            buildConfigField("String", "AUTH_URL", project.findProperty("GITHUB_AUTH_URL").toString())
            buildConfigField("String", "TOKEN_URL", project.findProperty("GITHUB_TOKEN_URL").toString())
            buildConfigField("String", "CLIENT_ID", project.findProperty("GITHUB_CLIENT_ID").toString())
            buildConfigField("String", "CLIENT_SECRET", project.findProperty("GITHUB_CLIENT_SECRET").toString())
            buildConfigField("String", "REDIRECT_URI", project.findProperty("GITHUB_REDIRECT_URI").toString())
            buildConfigField("String", "SCOPE", project.findProperty("GITHUB_SCOPE").toString())
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    runtimeOnly(libs.constraintlayout)

    testImplementation(libs.mockwebserver)
    testImplementation(libs.mock)
    testImplementation(libs.mockk.android)
    testImplementation(libs.core.testing)

    implementation(libs.fragment.ktx)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    implementation(libs.gson)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.glide)
    implementation(libs.circleimageview)

    implementation(libs.navigationFragmentKtx)
    implementation(libs.navigationUiKtx)
}