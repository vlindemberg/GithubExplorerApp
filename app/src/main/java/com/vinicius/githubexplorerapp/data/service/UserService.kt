package com.vinicius.githubexplorerapp.data.service

import com.vinicius.githubexplorerapp.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("user")
    suspend fun getUserInfo(@Header("Authorization") token: String): UserResponse
}
