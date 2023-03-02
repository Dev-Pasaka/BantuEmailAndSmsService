package online.pasaka.model.users

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(val id:String,val username:String)
