package com.example

import com.example.db.Database
import com.example.plugins.HeaderValidationPlugin
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    val dbUsername = environment.config.property("database.username").getString()
    val dbPassword = environment.config.property("database.password").getString()

    Database.connect(
        userName = dbUsername,
        password = dbPassword
    )

    install(HeaderValidationPlugin)

    configureSecurity()
    configureSerialization()
    configureRouting()
    configureSwagger()
}

private fun Application.configureSwagger() {
    routing {
        swaggerUI("/swagger","openapi/documentation.yaml")
    }
}
