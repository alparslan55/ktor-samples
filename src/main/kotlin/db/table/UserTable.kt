package com.example.db.table

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

/*
 * File: UserTable
 * Project: ktor-sample1
 * Author: Alparslan Güney
 * Created: 10.07.2025
 * Updated: 10.07.2025
 * Version: <Version number>
 * 
 * Copyright (C) 2025 Asis Elektronik ve Bilişim Sistemleri A.Ş.
 */
object UserTable  : IntIdTable(name = "user") {
    val email = varchar(name = "email", length = 255)
    val name = varchar(name = "name", length = 50)
    val surname = varchar(name = "surname", length = 50)
    val password = varchar(name = "password", length = 255)
}


data class UserDto(
    val email : String,
    val name : String,
    val surname : String,
    val password : String
)