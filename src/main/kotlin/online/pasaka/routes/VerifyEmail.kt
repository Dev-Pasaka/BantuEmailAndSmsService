package online.pasaka.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import online.pasaka.codegenerator.OtpCodeGenerator
import online.pasaka.client.email_services.EmailSender
import online.pasaka.model.email.EmailModel
import online.pasaka.model.email.EmailOtpResponse
import java.lang.Exception

/*
    * VerifyEmail() creates a post route that receives a json object
    * It also deserializes it into a kotlin object
 */
fun Route.verifyEmail() {
    authenticate("auth") {
        val otp = OtpCodeGenerator()
        val sendEmail = EmailSender()
        post("/verifyEmail") {
            val otpCodeSend = otp.generateCode()
            val email = call.receive<EmailModel>()

            //  The email is passed as an argument into the sendEmail method and
            //  the generated otp code is also passed as an argument
            // A json response is also sent to the client on status = sent/true
            try {
                sendEmail.sendEmail(email = email.email, otp = otpCodeSend)
                call.respond(
                    EmailOtpResponse(
                        status = "Sent",
                        code = otpCodeSend,
                        email = email.email,
                        message = "Success: Code sent to email ${email.email}"
                    )
                )

                // If the email is not sent a json response object is sent to the client.
            } catch (_: Exception) {
                call.respond(
                    EmailOtpResponse(
                        status = "Not sent",
                        code = "NULL",
                        email = email.email,
                        message = "Error:Code not sent"
                    )
                )
            }

        }
    }
}
