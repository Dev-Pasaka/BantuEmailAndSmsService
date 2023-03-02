package online.pasaka.model.email

import kotlinx.serialization.Serializable

@Serializable
data class PromotionalEmailsModel(val email:String, val message:String)
