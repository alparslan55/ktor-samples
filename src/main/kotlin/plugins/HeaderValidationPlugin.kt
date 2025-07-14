package com.example.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent
import io.ktor.server.response.respond

/*
 * File: HeaderValidationPlugin
 * Project: ktor-sample1
 * Author: Alparslan Güney
 * Created: 14.07.2025
 * Updated: 14.07.2025
 * Version: <Version number>
 * 
 * Copyright (C) 2025 Asis Elektronik ve Bilişim Sistemleri A.Ş.
 */
val HeaderValidationPlugin = createApplicationPlugin(name = "HeaderValidationPlugin") {
    onCall { call->
        val deviceId = call.request.headers["X-Device-Id"]
        if (deviceId == null) {
            call.respond(status = HttpStatusCode.BadRequest, message = HttpStatusCode.BadRequest.value)
        }
    }
    on(MonitoringEvent(ApplicationStarted)) {
        println("Hello Application Scoped Plugin")
    }
}