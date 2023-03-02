package online.pasaka.model.phone

import kotlinx.serialization.Serializable

@Serializable
data class PhoneOtpResponse<T>(
    val status:T,
    val code:String,
    val phone:String,
    val message:String
)

