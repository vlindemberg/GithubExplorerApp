package com.vinicius.githubexplorerapp.util

import com.vinicius.githubexplorerapp.domain.model.User

object UserLogged {
    private var authToken: String = ""
    private lateinit var user: User

    fun setAuthToken(token: String) {
        authToken = token
    }

    fun getAuthToken(): String {
        return authToken
    }

    fun setUserLogged(userLogged: User) {
        user = userLogged
    }

    fun getUserLogged(): User {
        return user
    }
}
