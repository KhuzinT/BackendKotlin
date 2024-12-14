package plugin

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.slf4j.event.Level

import module.postModule
import module.userModule

fun Application.configureServer() {
    install(Koin) {
        modules(postModule)
        modules(userModule)
    }
    install(CallLogging) {
        level = Level.DEBUG
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }
}