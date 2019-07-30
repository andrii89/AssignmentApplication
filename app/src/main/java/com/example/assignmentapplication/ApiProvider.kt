package com.example.assignmentapplication

import com.example.assignmentapplication.network.API

interface ApiProvider {
    fun getApi(): API
}