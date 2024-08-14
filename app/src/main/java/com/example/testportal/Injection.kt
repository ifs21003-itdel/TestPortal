package com.example.testportal

import android.content.Context
import com.example.testportal.network.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService(context)
        return Repository.getInstance(apiService)
    }
}