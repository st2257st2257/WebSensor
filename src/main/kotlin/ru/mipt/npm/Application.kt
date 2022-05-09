package ru.mipt.npm

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.mipt.npm.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureTemplating()
        configureSockets()
    }.start(wait = true)
}
