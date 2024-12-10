package route.post

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import route.post.request.PostRequest

import service.post.PostService

// хорошо бы return call.respond(StatusCode, message) вынести в отдельную сущность, но пока так

fun Route.postRoute(postService: PostService) {
    authenticate("auth-jwt") {
        // create post
        put("/post") {
            val principal =
                call.principal<JWTPrincipal>() ?: return@put call.respond(HttpStatusCode.Unauthorized, "Invalid token")
            val request = call.receive<PostRequest>()

            val username = getUsername(principal)
            call.respond(HttpStatusCode.OK, postService.create(request.text, username))
        }

        // update post
        patch("/post/{id}") {
            val id =
                call.parameters["id"]?.toInt() ?: return@patch call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing id parameter"
                )
            val principal = call.principal<JWTPrincipal>() ?: return@patch call.respond(
                HttpStatusCode.Unauthorized,
                "Invalid token"
            )
            val request = call.receive<PostRequest>()

            val username = getUsername(principal)
            postService.update(id, request.text, username)?.let { call.respond(HttpStatusCode.OK, it) }
                ?: return@patch call.respond(HttpStatusCode.Forbidden, "Does not have access")
        }

        // delete post
        delete("/post/{id}") {
            val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                "Missing id parameter"
            )
            val principal = call.principal<JWTPrincipal>() ?: return@delete call.respond(
                HttpStatusCode.Unauthorized,
                "Invalid token"
            )

            val username = getUsername(principal)
            postService.delete(id, username)?.let { call.respond(HttpStatusCode.OK, it) }
                ?: return@delete call.respond(HttpStatusCode.Forbidden, "Does not have access")
        }
    }

    // get all
    get("/posts") {
        call.respond(HttpStatusCode.OK, postService.getAll())
    }

    // get by id
    get("/post/{id}") {
        val id =
            call.parameters["id"]?.toInt() ?: return@get call.respond(
                HttpStatusCode.BadRequest, "Missing id parameter"
            )
        postService.getById(id)?.let { call.respond(HttpStatusCode.OK, it) }
            ?: return@get call.respond(HttpStatusCode.NotFound, "Post not found")
    }

    // get page
    get("/post/page") {
        val offset =
            call.request.queryParameters["offset"]?.toInt() ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                "Missing offset query parameter"
            )
        val limit = call.request.queryParameters["limit"]?.toInt() ?: return@get call.respond(
            HttpStatusCode.BadRequest,
            "Missing limit query parameter"
        )
        call.respond(HttpStatusCode.OK, postService.getPage(offset, limit))
    }
}

private fun getUsername(principal: JWTPrincipal): String = principal.payload.getClaim("username").asString()