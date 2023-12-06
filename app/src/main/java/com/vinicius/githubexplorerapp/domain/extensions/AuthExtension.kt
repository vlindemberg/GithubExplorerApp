package com.vinicius.githubexplorerapp.domain.extensions

import com.vinicius.githubexplorerapp.data.model.AuthTokenResponse
import com.vinicius.githubexplorerapp.domain.model.Token

fun AuthTokenResponse.toToken(): Token = Token(token = this.token, type =  this.tokenType)