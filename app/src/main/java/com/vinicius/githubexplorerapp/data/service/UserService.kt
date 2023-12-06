package com.vinicius.githubexplorerapp.data.service

import com.vinicius.githubexplorerapp.data.model.UserFollowersResponse
import com.vinicius.githubexplorerapp.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    @GET("user")
    suspend fun getUserInfo(@Header("Authorization") token: String): UserResponse

    @GET("users/{username}/following")
    suspend fun getUserFollowers(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ) : List<UserFollowersResponse>
}
