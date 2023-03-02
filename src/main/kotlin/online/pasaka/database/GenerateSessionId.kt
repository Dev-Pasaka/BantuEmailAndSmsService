package online.pasaka.database

import java.security.SecureRandom

class GenerateSessionId {
    fun sessionId(): String {
        val random = SecureRandom()
        val bytes = ByteArray(12)
        random.nextBytes(bytes)
        return  bytes.map { it.toInt() and 6 }.joinToString("")
    }
}