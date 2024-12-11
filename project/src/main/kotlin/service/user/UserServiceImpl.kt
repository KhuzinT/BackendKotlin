package service.user

import model.User
import repo.user.UserRepo

// да, надо хранить хеши, а не чистые пароли, но пока так

// еще не очень хорошо возвращать просто bool, а то пользователь не поймет, что конкретно сделал не так
// надо возвращать конкретную ошибку, например исключение бросать или как в go возвращать какой-то error из функции, но пока так

class UserServiceImpl(private val userRepo: UserRepo) : UserService {
    override fun login(username: String, password: String): Boolean {
        val user = userRepo.getByUsername(username) ?: return false
        return user.password == password
    }

    override fun register(username: String, password: String): Boolean {
        // здесь можно сделать проверку, что пароль не слишком короткий, но пока будет так
        return userRepo.register(User(username, password))
    }
}