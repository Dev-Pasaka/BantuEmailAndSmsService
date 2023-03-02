package online.pasaka.model.users

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id:Int?,
    val username:String?,
    var password:String?
    )
