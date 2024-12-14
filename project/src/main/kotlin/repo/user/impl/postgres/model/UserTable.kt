package repo.user.impl.postgres.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object UserTable : IntIdTable("users") {
    var username: Column<String> = varchar("username", 50).uniqueIndex()
    var password: Column<String> = varchar("password", 50)
}