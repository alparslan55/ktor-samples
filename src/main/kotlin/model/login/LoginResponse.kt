package com.example.model.login

import kotlinx.serialization.Serializable

/*
 * File: LoginResponse
 * Project: ktor-sample1
 * Author: Alparslan Güney
 * Created: 27.06.2025
 * Updated: 27.06.2025
 * Version: <Version number>
 * 
 * Copyright (C) 2025 Asis Elektronik ve Bilişim Sistemleri A.Ş.
 */
@Serializable
data class LoginResponse(
    val token : String
)