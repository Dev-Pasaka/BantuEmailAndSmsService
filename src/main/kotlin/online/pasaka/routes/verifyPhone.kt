package online.pasaka.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import online.pasaka.client.sms_services.PhoneVerification
import online.pasaka.codegenerator.OtpCodeGenerator
import online.pasaka.model.phone.PhoneModel
import online.pasaka.model.phone.PhoneOtpResponse

fun Route.verifyPhone(){
    authenticate("auth") {
    post("/verifyPhone"){
        val phoneNumber = call.receive<PhoneModel>()
        val otpCode = OtpCodeGenerator().generateCode()
        try {
            PhoneVerification().verifyPhone(otpCode = otpCode, phone = phoneNumber.phone)
            call.respond(
                PhoneOtpResponse(
                    status = "Sent",
                    code = otpCode,
                    phone = phoneNumber.phone,
                    message = "Success: Code sent to  ${phoneNumber.phone}"
                )
            )
        }catch (_:Exception){
            call.respond(
                PhoneOtpResponse(
                    status = "Not sent",
                    code = "NULL",
                    phone = phoneNumber.phone,
                    message = "Error:Code not sent"
                )
            )
        }
    }
}
}