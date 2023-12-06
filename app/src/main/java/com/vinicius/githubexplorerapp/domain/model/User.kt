package com.vinicius.githubexplorerapp.domain.model

data class User(
    val id: Int,
    val avatarUrl: String,
    val email: String,
    val login: String,
    val name: String,
) {
    constructor() : this(0, "", "", "", "")
}