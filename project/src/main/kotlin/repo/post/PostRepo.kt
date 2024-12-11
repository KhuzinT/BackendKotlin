package repo.post

import model.Post

interface PostRepo {
    fun create(text: String, username: String): Post

    fun update(uuid: Int, text: String): Post?

    fun delete(uuid: Int): Post?

    fun getAll(): List<Post>

    fun getSorted(): List<Post>

    fun getById(uuid: Int): Post?

    fun getPage(offset: Int, limit: Int): List<Post>
}