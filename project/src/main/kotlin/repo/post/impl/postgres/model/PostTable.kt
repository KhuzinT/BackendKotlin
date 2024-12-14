package repo.post.impl.postgres.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import repo.user.impl.postgres.model.UserTable

object PostTable : IntIdTable("posts") {
    val text: Column<String> = varchar("text", 50)
    val createdAt: Column<String> = varchar("created_at", 50)
    val updatedAt: Column<String> = varchar("updated_at", 50)
    val username: Column<String> = reference("username", UserTable.username)
}