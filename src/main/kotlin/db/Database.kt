package com.example.db

import com.example.db.table.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

/*
 * File: Database
 * Project: ktor-sample1
 * Author: Alparslan Güney
 * Created: 10.07.2025
 * Updated: 10.07.2025
 * Version: <Version number>
 * 
 * Copyright (C) 2025 Asis Elektronik ve Bilişim Sistemleri A.Ş.
 */
object Database {

    fun getDbConfig(
        userName: String,
        password: String
    ) = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/exampledatabase"
        driverClassName = "org.postgresql.Driver"
        username = userName
        this.password = password
        maximumPoolSize = 6
        isReadOnly = false
        transactionIsolation = "TRANSACTION_SERIALIZABLE"
    }

    fun connect(
        userName: String,
        password: String
    ): Database {
        val dataSource = HikariDataSource(
            getDbConfig(
                userName = userName,
                password = password
            )
        )
        val db = Database.connect(datasource = dataSource)

        transaction {
            SchemaUtils.create(UserTable)
        }

        return db
    }

}