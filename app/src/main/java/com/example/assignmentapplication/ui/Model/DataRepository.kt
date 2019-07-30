package com.example.assignmentapplication.ui.Model

import com.example.assignmentapplication.network.API
import com.example.assignmentapplication.network.BaseRepository

class DataRepository(val api: API): BaseRepository() {
    suspend fun getData(int : Int): ApiResponse?{
        return safeApiCall(
            call = { api.getData(page = int) },
            errorMessage = "Error Fetching Date from NHLApi")
    }
}