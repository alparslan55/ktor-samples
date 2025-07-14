package com.example.model.register

import kotlinx.serialization.Serializable

/*
 * File: RegisterResponse
 * Project: ktor-sample1
 * Author: Alparslan Güney
 * Created: 10.07.2025
 * Updated: 10.07.2025
 * Version: <Version number>
 * 
 * Copyright (C) 2025 Asis Elektronik ve Bilişim Sistemleri A.Ş.
 */
@Serializable
data class RegisterResponse(
    val result : Boolean
)