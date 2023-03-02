package online.pasaka.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import online.pasaka.client.sms_services.SmsNotifications
import online.pasaka.model.phone.PromotionalMessagesModel
import online.pasaka.model.phone.PromotionalMessagesResponse

fun Route.smsNotifications(){
    authenticate("auth") {
        post("/smsNotifications") {
            val smsNotificationBody = call.receive<PromotionalMessagesModel>()


            try {
                SmsNotifications().sendSmsNotification(
                    phone = smsNotificationBody.phone,
                    message = smsNotificationBody.message
                )
                call.respond(
                    PromotionalMessagesResponse(
                        status = "sent",
                        phone = smsNotificationBody.phone,
                        message = "Sms notification sent"
                    )
                )
            } catch (_: Exception) {
                call.respond(
                    PromotionalMessagesResponse(
                        status = "Not sent",
                        phone = smsNotificationBody.phone,
                        message = "Sms notification not sent"
                    )
                )
            }
        }
    }
}