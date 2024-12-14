package repo.post.impl.postgres.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PostEntity>(PostTable)

    var text by PostTable.text
    var createdAt by PostTable.createdAt
    var updatedAt by PostTable.updatedAt
    var username by PostTable.username
}