package com.vinicius.githubexplorerapp.data.service

import com.vinicius.githubexplorerapp.BuildConfig
import com.vinicius.githubexplorerapp.data.model.AuthTokenResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface AuthenticationService {
    @Headers("Accept: application/json")
    @POST
    suspend fun getAuthToken(
        @Url url: String = BuildConfig.TOKEN_URL,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String
    ): AuthTokenResponse
}