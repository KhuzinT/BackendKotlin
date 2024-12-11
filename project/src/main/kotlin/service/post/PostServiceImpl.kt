package service.post

import model.Post
import repo.post.PostRepo

class PostServiceImpl(private val postRepo: PostRepo) : PostService {
    override fun create(text: String, username: String): Post {
        // здесь можно сделать валидацию text, но пока так
        return postRepo.create(text, username)
    }

    override fun update(uuid: Int, text: String, username: String): Post? {
        return if (hasAccess(username, uuid)) {
            postRepo.update(uuid, text)
        } else null
    }

    override fun delete(uuid: Int, username: String): Post? {
        return if (hasAccess(username, uuid)) {
            postRepo.delete(uuid)
        } else null
    }

    override fun getAll(): List<Post> = postRepo.getSorted()

    override fun getById(uuid: Int): Post? = postRepo.getById(uuid)

    override fun getPage(offset: Int, limit: Int): List<Post> = postRepo.getPage(offset, limit)

    private fun hasAccess(username: String, uuid: Int): Boolean {
        val post = getById(uuid) ?: return false
        return post.username == username
    }
}