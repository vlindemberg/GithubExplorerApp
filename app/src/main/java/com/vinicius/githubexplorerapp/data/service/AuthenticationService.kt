package com.vinicius.githubexplorerapp.data.service

import android.media.session.MediaSession.Token
import com.vinicius.githubexplorerapp.BuildConfig
import com.vinicius.githubexplorerapp.data.model.AuthTokenResponse
import com.vinicius.githubexplorerapp.data.model.TokenRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
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

    @POST("/applications/{client_id}/grant")
    suspend fun deleteAuthToken(
        @Header("Authorization") tokenBearer: String,
        @Path("client_id") clientId: String,
        @Body token: TokenRequest,
    ): Any
}