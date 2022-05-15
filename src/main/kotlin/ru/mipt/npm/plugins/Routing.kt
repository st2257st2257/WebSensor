package ru.mipt.npm.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.mipt.npm.cmdREAD
import ru.mipt.npm.cmdSHUTDOWN

@OptIn(ExperimentalCoroutinesApi::class)
fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/READ") {
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
