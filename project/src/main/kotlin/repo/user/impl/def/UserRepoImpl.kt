package repo.user.impl.def

import model.User
import repo.user.UserRepo

class UserRepoImpl : UserRepo {
    private val users: MutableMap<String, User> = mutableMapOf()

    init {
        register(User("admin", "admin"))
        register(User("tim", "password"))
    }

    override fun register(user: User): Boolean {
        if (users.containsKey(user.username)) {
            return false
        }

        users[user.username] = user
        return true
    }

    override fun getByUsername(username: String): User? = users[username]
}