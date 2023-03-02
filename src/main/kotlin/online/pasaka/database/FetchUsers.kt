package online.pasaka.database

import io.ktor.server.application.*
import online.pasaka.model.users.User
import online.pasaka.model.users.UserCredentials
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt

class FetchUsers {
    fun fetchUsers(username:String? = "",password:String?):User?{
        val db = DatabaseConnection().database
        val userCredentials = UserCredentials(username!!,password!!)
        val username:String = UserCredentials(username,password).username
        var encryptedPassword:String

        try {
            val dbPassword = db.from(UsersEntity).select()
                .where {
                    UsersEntity.username eq username
                }
                .map {
                    val id = it[UsersEntity.id]
                    val username = it[UsersEntity.username]
                    val password = it[UsersEntity.password]
                    User(id = id, username = username, password = password)
                }
                .firstOrNull()
            encryptedPassword = dbPassword?.password.toString()
        }catch (_:NullPointerException){
            encryptedPassword = ""
        }

        val user = db.from(UsersEntity).select()
            .where {
                UsersEntity.password eq encryptedPassword
            }.map {
                val id = it[UsersEntity.id]
                val username = it[UsersEntity.username]
                val password = it[UsersEntity.password]
                User(id?:-1,username,password)
            }.firstOrNull()
        val hashPassword = user?.password
        var checkPassword = false
        if (hashPassword!=null){
            checkPassword = BCrypt.checkpw(userCredentials.password, hashPassword)
        }
        if (checkPassword){
            user?.password = userCredentials.password
        }
        return user
    }
}