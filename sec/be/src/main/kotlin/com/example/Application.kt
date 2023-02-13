package com.example

import com.example.config.Config
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.github.salomonbrys.kodein.instance
import com.example.config.config

fun main() {
    val conf = config.instance<Config> ()
    embeddedServer(Netty, port = conf.port, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureHTTP()
    configureSerialization()
    configureSockets()
    configureRouting()
    installGraphQL()
}

// EOF