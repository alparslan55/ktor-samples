package com.example.model.register

import kotlinx.serialization.Serializable

/*
 * File: RegisterRequest
 * Project: ktor-sample1
 * Author: Alparslan Güney
 * Created: 10.07.2025
 * Updated: 10.07.2025
 * Version: <Version number>
 * 
 * Copyright (C) 2025 Asis Elektronik ve Bilişim Sistemleri A.Ş.
 */
@Serializable
data class RegisterRequest(
    val name : String,
    val surname : String,
    val email : String,
    val password : String
)