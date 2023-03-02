package online.pasaka.model.users

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseBody(val success:Boolean, val message:String)
