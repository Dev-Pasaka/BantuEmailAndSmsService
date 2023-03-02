package online.pasaka.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.network.*
import online.pasaka.client.email_services.NewsAndOffers
import online.pasaka.model.email.EmailOtpResponse
import online.pasaka.model.email.PromotionalEmailsModel
import online.pasaka.model.email.PromotionalEmailsResponse
import java.lang.Exception

fun Route.newsAndOffers(){

    authenticate("auth") {
    post("/newsAndOffers") {
        val promotionalEmailBody = call.receive<PromotionalEmailsModel>()

        try {
            NewsAndOffers().sendEmail(
                email = promotionalEmailBody.email,
                message_body = promotionalEmailBody.message
            )
            call.respond(
                PromotionalEmailsResponse(
                    status = "Sent",
                    email = promotionalEmailBody.email,
                    message = "Promotional email sent"
                )
            )
        }catch (_: Exception) {
            call.respond(
                EmailOtpResponse(
                    status = "Not sent",
                    code = "NULL",
                    email = promotionalEmailBody.email,
                    message = "Error: Code not sent"
                )
            )
        }
    }
    }


}