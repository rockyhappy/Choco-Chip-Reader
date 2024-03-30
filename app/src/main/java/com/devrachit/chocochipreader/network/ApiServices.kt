package com.devrachit.chocochipreader.network

import com.devrachit.chocochipreader.Models.DetailsResponse
import com.devrachit.chocochipreader.Models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("generate/")
    suspend fun login(
        @Field("username") field1: String,
        @Field("password") field2: String
    ): Response<LoginResponse>

    @GET("details/{id}")
    suspend fun getDetails(
        @Path("id") id: String
    ): Response<DetailsResponse>
}