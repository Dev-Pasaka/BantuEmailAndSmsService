package online.pasaka.client.sms_services

import com.typesafe.config.ConfigFactory
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.config.*

class SmsNotifications {

    private val config = HoconApplicationConfig( ConfigFactory.load())

    suspend fun sendSmsNotification(
        username:String = "Bantu",
        phone:String,
        message:String="Here is out new offers for you ! "
    ): HttpStatusCode {
        val client = HttpClient(CIO){
            install(ContentNegotiation){
                json()
            }
        }
        val request = client.post(config.property("africastalking_baseurl").getString()){
            header("apikey",config.property("africastalking_api_key").getString())
            setBody(FormDataContent(Parameters.build {
                append("username", username)
                append("to",phone)
                append("message",message)
            }))
        }
        return request.status
    }
}