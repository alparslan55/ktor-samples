package com.example

import com.example.data.repository.UserRepository
import com.example.model.login.LoginRequest
import com.example.model.login.LoginResponse
import com.example.model.register.RegisterRequest
import com.example.model.register.RegisterResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {


    val userRepository = UserRepository()

    routing {
        post("/login") {
            val request = call.receive<LoginRequest>()

            val userName = request.username

            val user = userRepository.getUser(
                email = userName
            )

            if (user == null) {
                call.respond(
                    HttpStatusCode.Unauthorized
                )
                return@post
            }

            if (request.username == user.email && request.password == user.password) {
                val token = UserToken.createUserToken(userName = user.email)
                call.respond(
                    LoginResponse(
                        token = token
                    )
                )
            } else {
                call.respond(HttpStatusCode.Unauthorized)
            }
        }
        post("/register") {
            val request = call.receive<RegisterRequest>()
            val result = userRepository.insertUser(
                name = request.name,
                surname = request.surname,
                email = request.email,
                password = request.password
            )
            call.respond(
                HttpStatusCode.OK,
                RegisterResponse(
                    result = result
                )
            )
        }
        authenticate {
            get("/user") {
                val token = call.request.authorization()?.removePrefix("Bearer ")
                val userName = UserToken.decryptToken(token = token.orEmpty())
                call.respond("Hello $userName")
            }
        }
        get("/say-hello/{name}") {
            val name = call.parameters["name"]
            call.respondText("Hello $name")
        }
    }
}

