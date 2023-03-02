package online.pasaka.model.test

import kotlinx.serialization.Serializable

@Serializable
data class TestEndpointResponse(
    val status:String,
    val message: String
)
