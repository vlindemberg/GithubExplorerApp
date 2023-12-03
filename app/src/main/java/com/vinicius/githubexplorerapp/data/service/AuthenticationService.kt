package com.vinicius.githubexplorerapp.data.service

import com.vinicius.githubexplorerapp.data.model.AuthTokenResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationService {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    suspend fun getAuthToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String
    ): AuthTokenResponse
}