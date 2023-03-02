package online.pasaka.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.Identity.decode
import online.pasaka.database.DatabaseConnection
import online.pasaka.database.UsersEntity
import online.pasaka.model.test.TestEndpointResponse
import online.pasaka.model.users.User
import online.pasaka.model.users.UserResponse
import org.ktorm.dsl.*


fun Route.testEndpoint() {

    get("/test") {
        call.respond(
            HttpStatusCode.OK,
            TestEndpointResponse(
                message = "The api endpoint is working well !",
                status = "Endpoint is ok"
            )
        )
    }
    authenticate("auth") {
        get("/users") {
            val db = DatabaseConnection().database
            val user = db.from(UsersEntity).select()
                .map {
                    val id = it[UsersEntity.id].toString()
                    val username = it[UsersEntity.username]
                    UserResponse(id , username!!)
                }
            call.respond(user)
        }
    }
}