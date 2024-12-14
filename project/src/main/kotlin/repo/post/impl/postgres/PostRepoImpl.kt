package repo.post.impl.postgres

import model.Post
import org.jetbrains.exposed.sql.transactions.transaction
import repo.post.PostRepo
import repo.post.impl.postgres.model.PostEntity

class PostRepoImpl : PostRepo {
    override fun create(text: String, username: String): Post {
        return transaction {
            val currTime = System.currentTimeMillis().toString()
            PostEntity.new {
                this.text = text
                this.createdAt = currTime
                this.updatedAt = currTime
                this.username = username
            }.toPost()
        }
    }

    override fun update(uuid: Int, text: String): Post? {
        return transaction {
            PostEntity.findByIdAndUpdate(uuid) {
                it.text = text
                it.updatedAt = System.currentTimeMillis().toString()
            }?.toPost()
        }
    }

    override fun delete(uuid: Int): Post? {
        return transaction {
            val postEntity = PostEntity.findById(uuid) ?: return@transaction null
            val post = postEntity.toPost()
            postEntity.delete()
            post
        }
    }

    override fun getAll(): List<Post> {
        return transaction {
            PostEntity.all().map { it.toPost() }
        }
    }

    override fun getSorted(): List<Post> = getAll().sortedBy { it.uuid }

    override fun getById(uuid: Int): Post? {
        return transaction {
            PostEntity.findById(uuid)?.toPost()
        }
    }

    override fun getPage(offset: Int, limit: Int): List<Post> {
        val sorted = getSorted()
        val a = minOf(sorted.size, offset)
        val b = minOf(sorted.size, offset + limit)
        return sorted.subList(a, b)
    }

    private fun PostEntity.toPost() =
        Post(uuid = id.value, text = text, createdAt = createdAt, updatedAt = updatedAt, username = username)
}