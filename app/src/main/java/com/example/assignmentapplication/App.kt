package com.example.assignmentapplication

import android.app.Application
import com.example.assignmentapplication.network.API
import com.example.assignmentapplication.network.ApiFactory

class App : Application(), ApiProvider {

    private lateinit var api: API

    override fun onCreate() {
        super.onCreate()
        api = ApiFactory.getAPI(BuildConfig.BASE_URL)

    }

    override fun getApi(): API {
        return api
    }
}