package com.devrachit.chocochipreader.network

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.devrachit.chocochipreader.BuildConfig
import com.devrachit.chocochipreader.datastore.readFromDataStore
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit







/**
 * These are the retrofit instance for the jwt Authentication
 */

object RetrofitInstance2 {
    private const val BASE_URL = BuildConfig.baseUrl

    private fun createApiService(jwtToken: String): ApiService {


        val authInterceptor = AuthInterceptor(jwtToken)
        val timeout = 30L // Adjust this value as needed
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    // Function to get the JWT token from DataStore
    suspend fun getApiServiceWithToken(dataStore: DataStore<Preferences>): ApiService {
        val jwtToken = readFromDataStore(dataStore, "token").toString()
        return createApiService(jwtToken)
    }
}