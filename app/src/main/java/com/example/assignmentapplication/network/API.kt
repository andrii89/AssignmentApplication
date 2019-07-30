package com.example.assignmentapplication.network

import com.example.assignmentapplication.ui.Model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("/api/v1/search_by_date")
    suspend fun getData(@Query("tags") tags: String = "story", @Query("page") page: Int): Response<ApiResponse>

}