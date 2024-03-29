package com.devrachit.chocochipreader.network

import com.devrachit.chocochipreader.Models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/cart/delete-cart-item")
    suspend fun deleteCartItem(@Body request: Int): Response<String>

    @FormUrlEncoded
    @POST("generate/")
    suspend fun login(
        @Field("username") field1: String,
        @Field("password") field2: String
    ) : Response<LoginResponse>
}