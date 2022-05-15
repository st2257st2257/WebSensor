package ru.mipt.npm.plugins

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.html.*
import ru.mipt.npm.cmdREAD
import ru.mipt.npm.cmdSHUTDOWN


@OptIn(ExperimentalCoroutinesApi::class)
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
                        h3 { +str }
                        h6 { +"ddd"}
                        button ( type = ButtonType.button ){
                            text("hhh")
                            //onClick = "alert('Hello');"
                        }
                        div {
                            img {
                                src = "https://placekitten.com/408/287"
                            }
                        }
                        script {
                            //script(src = "C:\\MIPT\\PROG\\Kotlin\\HW\\js.js") {}
                        }
                        script{
                            unsafe{ +"""window.setInterval('window.location.reload()', 1000);"""}
                        }
                    }
                }
            }
        }
        get("/html-shutdown") {
            cmdSHUTDOWN()
            call.respondHtml {
                body {
                            h1 { +"HTML" }
                            h2 { +"SHUTDOWN" }
                    }
            }
        }
    }
}
