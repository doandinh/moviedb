object Dependencies {
    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
        const val DATASTORE_PREFERENCES = "androidx.datastore:datastore-preferences:${Versions.DATASTORE_PREFERENCES}"
        const val SECURITY_CRYPTO = "androidx.security:security-crypto:${Versions.SECURITY_CRYPTO}"
        const val ANDROIDX_ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ANDROIDX_ACTIVITY_KTX_VERSION}"
        const val ANDROIDX_SUPPORT = "androidx.appcompat:appcompat:${Versions.ANDROIDX_SUPPORT_VERSION}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
        const val ANDROIDX_FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.ANDROIDX_FRAGMENT_KTX_VERSION}"
        const val ANDROIDX_NAVIGATION = "androidx.navigation:navigation-fragment-ktx:${Versions.ANDROIDX_NAVIGATION_VERSION}"
        const val ANDROIDX_NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.ANDROIDX_NAVIGATION_VERSION}"
        const val ANDROIDX_LIFECYCLE_SERVICE = "androidx.lifecycle:lifecycle-service:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    }

    object Hilt {
        const val ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"

        const val JAVAX_INJECT = "javax.inject:javax.inject:${Versions.JAVAX_INJECT}"
    }

    object Kotlin {
        const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINES}"
    }

    object Log {
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

        const val CHUCKER = "com.github.chuckerteam.chucker:library:${Versions.CHUCKER}"
        const val CHUCKER_NO_OP = "com.github.chuckerteam.chucker:library-no-op:${Versions.CHUCKER}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"

        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

        const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
        const val MOSHI_ADAPTERS = "com.squareup.moshi:moshi-adapters:${Versions.MOSHI}"
        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"
    }

    object Util {
        const val COIL = "io.coil-kt:coil:${Versions.COIL}"
    }

    object Test {
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLIN_COROUTINES}"

        const val JUNIT = "junit:junit:${Versions.JUNIT}"

        const val KOTEST = "io.kotest:kotest-assertions-core:${Versions.KOTEST}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"

        const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"

        const val TEST_CORE = "androidx.test:core:${Versions.CORE}"
        const val TURBINE = "app.cash.turbine:turbine:${Versions.TURBINE}"
    }
}
