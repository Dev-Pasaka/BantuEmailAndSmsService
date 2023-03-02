package online.pasaka.model.email

import kotlinx.serialization.Serializable

@Serializable
data class PromotionalEmailsResponse(
    val status:String,
    val email:String,
    val message:String
    )
