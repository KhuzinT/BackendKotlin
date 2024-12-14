package app

import io.ktor.server.engine.*
import io.ktor.server.netty.*

import plugin.configureAuth
import plugin.configureDb
import plugin.configureRoutes
import plugin.configureServer

fun main() {
    embeddedServer(Netty, port = 8080) {
        configureDb()
        configureAuth()
        configureServer()
        configureRoutes()
    }.start(wait = true)
}