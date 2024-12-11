package repo.post

import model.Post

class PostRepoImpl : PostRepo {
    private val posts: MutableMap<Int, Post> = mutableMapOf()
    private var currUuid: Int = 0

    init {
        create("Test post", "admin")
        create("Hello, world!", "tim")
    }

    override fun create(text: String, username: String): Post {
        val uuid = currUuid++
        val currTime = System.currentTimeMillis().toString()

        val post = Post(uuid, text, currTime, currTime, username)
        posts[uuid] = post

        return post
    }

    override fun update(uuid: Int, text: String): Post? {
        val post = posts[uuid] ?: return null

        val newPost = post.copy(text = text, updatedAt = System.currentTimeMillis().toString())
        posts[uuid] = newPost

        return newPost
    }

    override fun delete(uuid: Int): Post? = posts.remove(uuid)

    override fun getAll(): List<Post> = posts.values.toList()

    override fun getSorted(): List<Post> = posts.values.sortedBy { it.uuid }

    override fun getById(uuid: Int): Post? = posts[uuid]

    override fun getPage(offset: Int, limit: Int): List<Post> {
        val a = minOf(posts.size, offset)
        val b = minOf(posts.size, offset + limit)
        return getSorted().subList(a, b)
    }
}