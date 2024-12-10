package service.post

import model.Post

interface PostService {
    fun create(text: String, username: String): Post

    fun update(uuid: Int, text: String, username: String): Post?

    fun delete(uuid: Int, username: String): Post?

    fun getAll(): List<Post>

    fun getById(uuid: Int): Post?

    fun getPage(offset: Int, limit: Int): List<Post>
}