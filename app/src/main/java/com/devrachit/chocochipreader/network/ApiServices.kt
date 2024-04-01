package com.devrachit.chocochipreader.network

import com.devrachit.chocochipreader.Models.DetailsResponse
import com.devrachit.chocochipreader.Models.LoginRequest
import com.devrachit.chocochipreader.Models.LoginResponse
import com.devrachit.chocochipreader.Models.MarkPresentRequest
import com.devrachit.chocochipreader.Models.MarkPresentResponse
import com.devrachit.chocochipreader.Models.finalAtendenceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("generateToken/")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("attendance/details/{id}")
    suspend fun getDetails(
        @Path("id") id: String
    ): Response<DetailsResponse>

    @POST("attendance/mark_present/")
    suspend fun markPresent(
        @Body request: MarkPresentRequest
    ): Response<MarkPresentResponse>

    @GET("attendance/present_students/{day}")
    suspend fun checkAttendance(
        @Path("day") day: String
    ): Response<List<DetailsResponse>>

}