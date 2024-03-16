package com.doan.example.util

import com.doan.example.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal const val AUTH_HEADER = "Authorization"
internal const val BEARER_TOKEN_PREFIX = "Bearer"

class AuthInterceptor @Inject constructor() : Interceptor {
    @Suppress("ReturnCount")
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .newBuilder()
            .header(
                name = AUTH_HEADER,
                value = "$BEARER_TOKEN_PREFIX ${BuildConfig.API_KEY}"
            )
            .build()
            .let { chain.proceed(it) }
    }
}
