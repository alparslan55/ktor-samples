package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

val jwtAudience = "jwt-audience"
val jwtDomain = "https://jwt-provider-domain/"
val jwtRealm = "ktor sample app"
val jwtSecret = "secret"

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}

object UserToken {
    fun createUserToken(
        userName: String
    ): String {
        return JWT.create()
            .withIssuer(jwtDomain)
            .withAudience(jwtAudience)
            .withClaim("userName", userName)
            .sign(Algorithm.HMAC256(jwtSecret))
    }
    fun decryptToken(token : String) : String {

        val jwt = JWT.require(Algorithm.HMAC256(jwtSecret)).build()

        return jwt.verify(token)
            .getClaim("userName").asString()
    }
}
