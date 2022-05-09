package ru.mipt.npm.plugins

import io.ktor.server.html.*
import kotlinx.html.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import ru.mipt.npm.cmdREAD
import ru.mipt.npm.cmdSHUTDOWN

fun Application.configureTemplating() {

    routing {
        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }
        get("/html-read") {
            val strings = cmdREAD()
            strings.await()
            if( strings.getCompleted().isNotEmpty() ) {
                val str = strings.getCompleted().elementAt(0).replace( "VALUE", "" )
                call.respondHtml {
                    body {
                        h1 { +"HTML" }
                        h2 { +"VALEU" }
                        h3 { +"$str" }
                    }
                }
            }
        }
        get("/html-shutdown") {
            val strings = cmdSHUTDOWN()
            call.respondHtml {
                body {
                            h1 { +"HTML" }
                            h2 { +"SHUTDOWN" }
                    }
            }
        }
    }
}
