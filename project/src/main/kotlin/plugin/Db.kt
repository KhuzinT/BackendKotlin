package plugin

import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

import repo.post.impl.postgres.model.PostTable
import repo.user.impl.postgres.model.UserTable

fun Application.configureDb() {
    val config = HoconApplicationConfig(ConfigFactory.load())

    val url = config.property("db.url").getString()
    val username = config.property("db.username").getString()
    val password = config.property("db.password").getString()

    val db = Database.connect(url, user = username, password = password)
    db.init()
}

private fun Database.init() {
    transaction(this) {
        SchemaUtils.create(PostTable)
        SchemaUtils.create(UserTable)
    }
}
