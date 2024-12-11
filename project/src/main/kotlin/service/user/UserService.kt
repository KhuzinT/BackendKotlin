package service.user

interface UserService {
    fun login(username: String, password: String): Boolean

    fun register(username: String, password: String): Boolean
}