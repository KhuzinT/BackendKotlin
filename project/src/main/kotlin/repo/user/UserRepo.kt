package repo.user

import model.User

interface UserRepo {
    fun register(user: User): Boolean

    fun getByUsername(username: String): User?
}