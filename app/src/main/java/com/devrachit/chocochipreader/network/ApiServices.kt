package com.devrachit.chocochipreader.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/cart/delete-cart-item")
    suspend fun deleteCartItem(@Body request: Int): Response<String>
}