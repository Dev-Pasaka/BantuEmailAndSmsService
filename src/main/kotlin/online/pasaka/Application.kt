package online.pasaka

import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import online.pasaka.plugins.*

/*
    * Configuration of the server Netty

 */



fun main() {
    embeddedServer(Netty, port = (System.getenv("PORT")?:"5000").toInt(),
        host ="0.0.0.0", module = Application::module)
        .start(wait = true)
}

/*
    * Installation of plugins
 */
fun Application.module() {
    configureSerialization()
    configureSecurity()
    configureRouting()

}
