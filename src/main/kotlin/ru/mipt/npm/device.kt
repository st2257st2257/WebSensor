package ru.mipt.npm

import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import ru.mipt.npm.plugins.EchoApp


fun cmdREAD() = CoroutineScope(Dispatchers.Default).async {
    val strings: ArrayList<String> = ArrayList()
    runBlocking {

        launch(Dispatchers.IO) {
            val ipLocal = "192.168.1.69"
            val ipGlobal = "46.138.245.249"
            val socket = aSocket(EchoApp.selectorManager).tcp().connect(ipGlobal, port = EchoApp.DefaultPort)
            val read = socket.openReadChannel()
            val write = socket.openWriteChannel(autoFlush = true)

            read.readUTF8Line()
//            println("server: $hellow")
            write.writeStringUtf8("READ\n")
            val line = read.readUTF8Line()
            if( line != null ) {
//                println("server: $line")
                strings.add( line )
            }

        }

    }
    return@async strings
}

fun cmdSHUTDOWN() {
    runBlocking {

        val socket = aSocket(EchoApp.selectorManager).tcp().connect("192.168.1.69", port = EchoApp.DefaultPort)
        socket.openReadChannel()
        val write = socket.openWriteChannel(autoFlush = true)

        launch(Dispatchers.IO) {
            write.writeStringUtf8("SHUTDOWN\n")
        }
    }
}