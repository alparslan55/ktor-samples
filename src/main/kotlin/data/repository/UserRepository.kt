package com.example.data.repository

import com.example.db.table.UserDto
import com.example.db.table.UserTable
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

/*
 * File: UserRepository
 * Project: ktor-sample1
 * Author: Alparslan Güney
 * Created: 10.07.2025
 * Updated: 10.07.2025
 * Version: <Version number>
 * 
 * Copyright (C) 2025 Asis Elektronik ve Bilişim Sistemleri A.Ş.
 */
class UserRepository {

    fun insertUser(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Boolean = transaction {
        return@transaction UserTable.insert {
            it[UserTable.name] = name
            it[UserTable.surname] = surname
            it[UserTable.email] = email
            it[UserTable.password] = password
        }.insertedCount > 0
    }

    fun getUser(
        email: String
    ): UserDto? = transaction {
        return@transaction UserTable.selectAll()
            .where { UserTable.email eq email }
            .map { resultRow ->
                UserDto(
                    email = resultRow[UserTable.email],
                    name = resultRow[UserTable.name],
                    surname = resultRow[UserTable.surname],
                    password = resultRow[UserTable.password]
                )
            }.singleOrNull()
    }
}