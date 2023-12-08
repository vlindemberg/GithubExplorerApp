package com.vinicius.githubexplorerapp.data.service

import com.vinicius.githubexplorerapp.data.model.UserFollowersResponse
import com.vinicius.githubexplorerapp.data.model.UserRepoRequest
import com.vinicius.githubexplorerapp.data.model.UserRepoResponse
import com.vinicius.githubexplorerapp.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("user")
    suspend fun getUserInfo(
        @Header("Authorization") token: String,
    ): UserResponse

    @GET("user/repos")
    suspend fun getUserRepos(
        @Header("Authorization") token: String,
    ): List<UserRepoResponse>

    @POST("user/repos")
    suspend fun createUserRepo(
        @Header("Authorization") token: String,
        @Body repo: UserRepoRequest,
    ): UserRepoResponse

    @GET("users/{username}/following")
    suspend fun getUserFollowers(
        @Header("Authorization") token: String,
        @Path("username") username: String,
    ): List<UserFollowersResponse>

    @GET("users/{username}/repos")
    suspend fun getFollowerUserRepos(
        @Header("Authorization") token: String,
        @Path("username") username: String,
    ): List<UserRepoResponse>
}
