package com.devrachit.chocochipreader.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val jwtToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $jwtToken")
        val request = requestBuilder.build()
        Log.d("JWT_TOKEN", jwtToken)
        return chain.proceed(request)
    }
}
