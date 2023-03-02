package online.pasaka.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import online.pasaka.routes.*

fun Application.configureRouting() {
    routing {
        verifyEmail()
        newsAndOffers()
        testEndpoint()
        home()
        verifyPhone()
        smsNotifications()
        registerUsers()
    }
}
