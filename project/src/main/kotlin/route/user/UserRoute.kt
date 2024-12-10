package route.user

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import route.user.request.UserRequest
import route.user.response.TokenResponse

import service.user.UserService
import java.util.*

fun Routing.userRoute(userService: UserService, config: ApplicationConfig) {
    val secret = config.property("jwt.secret").getString()
    val issuer = config.property("jwt.issuer").getString()
    val audience = config.property("jwt.audience").getString()

    post("/user/login") {
        val request = call.receive<UserRequest>()
        if (userService.login(request.username, request.password)) {
            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", request.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 3_600_600))
                .sign(Algorithm.HMAC256(secret))
            call.respond(HttpStatusCode.OK, TokenResponse(token))
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Invalid login")
        }
    }

    post("/user/register") {
        val request = call.receive<UserRequest>()
        if (userService.register(request.username, request.password)) {
            call.respond(HttpStatusCode.OK, "You have successfully registered")
        } else {
            call.respond(HttpStatusCode.BadRequest, "User already exists")
        }
    }
}