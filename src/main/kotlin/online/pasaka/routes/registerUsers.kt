package online.pasaka.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import online.pasaka.database.DatabaseConnection
import online.pasaka.database.UsersEntity
import online.pasaka.model.users.UserCredentials
import online.pasaka.model.users.UserResponseBody
import org.ktorm.dsl.*
import java.util.*

fun Route.registerUsers(){
    authenticate("auth"){
    post("/registerUsers"){
        val db = DatabaseConnection().database
        val user = call.receive<UserCredentials>()
        val username = user.username.lowercase(Locale.getDefault())
        val password = user.hashedPassword()
        val usernameQuery = db.from(UsersEntity).select()
            .where { UsersEntity.username eq user.username }
            .map { it[UsersEntity.username] }
            .firstOrNull()
        if (usernameQuery == null){
            val result = db.insert(UsersEntity){
                set(it.username,username)
                set(it.password,password)
            }
            if (result == 1){
                //Send a success response to the client
                call.respond(
                    HttpStatusCode.OK,UserResponseBody(
                        success =true,
                        message ="New user created successfully"
                    )
                )
            }else{
                // Send failed response to the client
                call.respond(
                    HttpStatusCode.BadRequest,UserResponseBody(
                        success = false,
                        message ="Failed to create new user"
                    )
                )
            }
        }else{
            call.respond(HttpStatusCode.OK,"Username already exists")
        }

    }
}
}