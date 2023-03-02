package online.pasaka.model.phone

import kotlinx.serialization.Serializable

@Serializable
data class PromotionalMessagesResponse(
    val status:String,
    val phone:String,
    val message:String
)
