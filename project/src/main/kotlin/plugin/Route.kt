package plugin

import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

import route.post.postRoute
import route.user.userRoute

import service.post.PostService
import service.user.UserService

fun Application.configureRoutes() {
    val config = HoconApplicationConfig(ConfigFactory.load())

    val postService by inject<PostService>()
    val userService by inject<UserService>()

    routing {
        postRoute(postService)
        userRoute(userService, config)
    }
}