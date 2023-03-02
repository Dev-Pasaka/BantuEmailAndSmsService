package online.pasaka.model.email

import kotlinx.serialization.Serializable


@Serializable
data class EmailOtpResponse<T>(
    val status:T,
    val code:String,
    val email:String,
    val message:String
    )
