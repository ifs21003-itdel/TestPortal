package com.example.testportal.network.retrofit

import com.example.testportal.network.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") totalPage: Int
    ): Response
}