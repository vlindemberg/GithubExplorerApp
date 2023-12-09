package com.vinicius.githubexplorerapp.util

import com.vinicius.githubexplorerapp.domain.model.User

object UserLogged {
    private var authToken: String = ""
    private var authTokenWithoutBearer: String = ""
    private lateinit var user: User

    fun setAuthToken(token: String) {
        authToken = token
    }

    fun getAuthToken(): String {
        return authToken
    }

    fun setAuthTokenWithoutBearer(tokenWithoutBearer: String) {
        authTokenWithoutBearer = tokenWithoutBearer
    }

    fun getAuthTokenWithoutBearer(): String {
        return authTokenWithoutBearer
    }

    fun setUserLogged(userLogged: User) {
        user = userLogged
    }

    fun getUserLogged(): User {
        return user
    }
}
