package model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val uuid: Int,
    val text: String,
    val createdAt: String,
    val updatedAt: String,
    val username: String,
)