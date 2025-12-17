android {
    namespace = "com.example.theme"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.theme"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
        multiDexEnabled = true

        splits {
            abi {
                isEnable = true
                reset()
                include("arm64-v8a") // ✅ 只生成一个 APK
                isUniversalApk = false
            }
        }

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

    flavorDimensions.add("distribution")
    productFlavors {
        create("fdroid") {
            dimension = "distribution"
            applicationIdSuffix = ".fdroid"
            buildConfigField("String", "DISTRIBUTION", "\"F-Droid\"")
        }
        create("playstore") {
            dimension = "distribution"
            buildConfigField("String", "DISTRIBUTION", "\"Play Store\"")
        }
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    // ✅ 移除 applicationVariants.all 中的多 ABI 输出逻辑
    // 直接使用默认命名即可
}
