package route.post.request

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val text: String,
)