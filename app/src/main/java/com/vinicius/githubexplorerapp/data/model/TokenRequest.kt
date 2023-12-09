package com.vinicius.githubexplorerapp.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TokenRequest(
    @SerializedName("access_token")
    val token: String
)