package online.pasaka.database

import org.ktorm.database.Database

class DatabaseConnection {
    val database = Database.connect(
        url = "jdbc:mysql://esilxl0nthgloe1y.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/zz95i5v5vpometr0",
        driver  = "com.mysql.cj.jdbc.Driver",
        user = "dk4dou5vj0tdxgze",
        password = "vywaulxnk1peg4cs"
    )
}