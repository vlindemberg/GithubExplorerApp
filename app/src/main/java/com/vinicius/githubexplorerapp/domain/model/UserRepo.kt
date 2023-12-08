package com.vinicius.githubexplorerapp.domain.model

data class UserRepo(
    val name: String,
    val description: String?,
    val homepage: String?,
    val private: Boolean = false,
    val isTemplate: Boolean = true,
)
