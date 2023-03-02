package online.pasaka.plugins

import io.ktor.client.engine.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import online.pasaka.database.*
import online.pasaka.model.users.User
import online.pasaka.model.users.UserCredentials
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt


fun Application.configureSecurity() {

	install(Authentication){
		basic("auth") {
			realm = "/verifyEmail"
			validate { credentials ->
				val cr = FetchUsers().fetchUsers(username = credentials.name,password = credentials.password)
				//val sessionName = FetchSessions().fetchSession(credentials.name)
				if (cr != null) {
					if (credentials.name == cr.username && credentials.password==cr.password) {
						UserIdPrincipal(credentials.name)
					} else {
						null
					}
				}else{
					null
				}

			}

		}
	}
}

