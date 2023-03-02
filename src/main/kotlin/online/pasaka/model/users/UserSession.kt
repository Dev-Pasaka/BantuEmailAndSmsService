package online.pasaka.model.users

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(val username:String?, val sessionId:String?)
