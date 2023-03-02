package online.pasaka.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.home(){
    get("/"){
        call.respondText("Welcome to bantu")
    }
}