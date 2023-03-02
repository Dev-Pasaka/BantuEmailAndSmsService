package online.pasaka.model.phone

import kotlinx.serialization.Serializable

@Serializable
data class PromotionalMessagesModel(val phone:String, val message:String)
