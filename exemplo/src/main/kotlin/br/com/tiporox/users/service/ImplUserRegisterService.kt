package br.com.tiporox.users.service

import br.com.tiporox.users.model.User
import io.micronaut.core.annotation.Order
import javax.inject.Singleton

@Singleton
@Order(15)
class ImplUserRegisterService : UserRegisterService {

    override var usersList: MutableList<User> = ArrayList<User>()

    override fun execute(id: String, name: String): User {
        val user = User(id, name, 18, "F")
        usersList.add(user)
        return user
    }

    override fun execute(user: User): User {
        usersList.add(user)
        return user
    }


}