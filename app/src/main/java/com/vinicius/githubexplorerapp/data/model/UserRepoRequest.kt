package com.vinicius.githubexplorerapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserRepoRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("private")
    val private: Boolean,
    @SerializedName("is_template")
    val isTemplate: Boolean,
)