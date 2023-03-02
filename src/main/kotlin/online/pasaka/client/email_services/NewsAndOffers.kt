package online.pasaka.client.email_services

import com.typesafe.config.ConfigFactory
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import io.ktor.server.config.*

class NewsAndOffers {

    // Initialization of the configuration file
    val config = HoconApplicationConfig( ConfigFactory.load())

    // The sendEmail() sends emails to the mailgun email services api endpoint
    suspend fun sendEmail(
        to :String="to",
        subject:String = "\uD83E\uDD73 New Deals & Offers \uD83C\uDF81 ",
        email:String?,
        message_body:String?
    ):String{

        // Initialization of the client engine object to send request to api endpoints
        val client  = HttpClient(CIO){

            install(ContentNegotiation){
                gson()
            }

            install(Logging)
        }

        val sendEmailRequest = client.post(
            config.property("mailgun_baseurl").getString()
        )
        {

            url {

                basicAuth("api", config.property("mailgun_api_key").getString())
                parameter("from", config.property("sender").getString())
                parameter(to, email)
                parameter("subject", subject)
                parameter("template", "offers")
                parameter("h:X-Mailgun-Variables", "{\"message_body\": \"$message_body\"}")
            }


        }
        return sendEmailRequest.body()
    }

}