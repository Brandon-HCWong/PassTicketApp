plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(AppVersion.COMPILED_SDK_VERSION)

    defaultConfig {
        applicationId = "com.example.passticketapp"
        minSdkVersion(AppVersion.MIN_SDK_VERSION)
        targetSdkVersion(AppVersion.TARGET_SDK_VERSION)
        versionCode = AppVersion.VERSION_CODE
        versionName = AppVersion.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Libs.KOTLIN}")
    implementation("com.google.android.material:material:${Libs.ANDROIDX_MATERIAL_VERSION}")

    implementation("androidx.core:core-ktx:${Libs.ANDROIDX_CORE_VERSION}")
    implementation("androidx.appcompat:appcompat:${Libs.ANDROIDX_APPCOMPAT_VERSION}")
    implementation("androidx.fragment:fragment-ktx:${Libs.ANDROIDX_FRAGMENT_VERSION}")
    implementation("androidx.constraintlayout:constraintlayout:${Libs.ANDROIDX_CONSTRAINT_LAYOUT_VERSION}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Libs.ANDROIDX_NAVIGATION_VERSION}")
    implementation("androidx.navigation:navigation-ui-ktx:${Libs.ANDROIDX_NAVIGATION_VERSION}")
    implementation("androidx.room:room-runtime:${Libs.ANDROIDX_ROOM_VERSION}")
    implementation("androidx.room:room-ktx:${Libs.ANDROIDX_ROOM_VERSION}")

    // Annotation Processor
    annotationProcessor("androidx.room:room-compiler:${Libs.ANDROIDX_ROOM_VERSION}")

    // Unit Test
    testImplementation("junit:junit:${Libs.JUNIT_VERSION}")
    testImplementation("org.mockito:mockito-core:${Libs.MOCKITO_CORE_VERSION}")
    testImplementation("io.mockk:mockk:${Libs.MOCKK_VERSION}")
    testImplementation("androidx.arch.core:core-testing:${Libs.ARCH_CORE_VERSION}")

    // UI Test
    androidTestImplementation("androidx.test.ext:junit:${Libs.ANDROIDX_JUNIT_VERSION}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Libs.ANDROIDX_ESPRESSO_VERSION}")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:${Libs.ANDROIDX_ESPRESSO_CONTRIB_VERSION}")

}