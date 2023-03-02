package online.pasaka.database

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UsersEntity :Table<Nothing>("Users"){
    val id = int("id").primaryKey()
    val username = varchar("Username")
    val password = varchar("Password")
}
