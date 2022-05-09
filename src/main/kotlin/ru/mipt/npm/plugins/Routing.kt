package ru.mipt.npm.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import ru.mipt.npm.cmdREAD
import ru.mipt.npm.cmdSHUTDOWN

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/console/read") {
            val strings = cmdREAD()
            strings.await()
            if( strings.getCompleted().isNotEmpty() ) {
                val str = strings.getCompleted().elementAt(0)
                println( "device: $str" )
                call.respondText("device: $str")
            }
        }
        get("/console/shutdown") {
            cmdSHUTDOWN()
            println( "device: shutdown" )
            call.respondText("device: shutdown")
        }

    }
}
