package repo.user.impl.postgres

import model.User
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import repo.user.UserRepo
import repo.user.impl.postgres.model.UserEntity
import repo.user.impl.postgres.model.UserTable

class UserRepoImpl : UserRepo {
    override fun register(user: User): Boolean {
        return transaction {
            if (getByUsername(user.username) == null) {
                UserEntity.new {
                    this.username = user.username
                    this.password = user.password
                }
                true
            } else false
        }
    }

    override fun getByUsername(username: String): User? {
        return transaction {
            UserEntity.find(UserTable.username eq username).firstOrNull()?.toUser()
        }
    }

    private fun UserEntity.toUser(): User = User(username = username, password = password)
}