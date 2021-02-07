package br.com.tiporox.users.service

import br.com.tiporox.users.model.User
import io.micronaut.core.annotation.Order
import io.micronaut.runtime.context.scope.Refreshable
import javax.annotation.PostConstruct
import javax.inject.Named
import javax.inject.Singleton

@Refreshable
@Order(15)
class UserService(
    // @param:Named("impl") private var userRegisterService: UserRegisterService
    private var userRegisterService: UserRegisterService
) {

    fun register(id: String, name: String) : User {
        return userRegisterService.execute(id, name)
    }

    fun register(user: User) : User {
        return userRegisterService.execute(user)
    }

    fun list(): List<User> {
        return userRegisterService.usersList
    }

    @PostConstruct
    fun init() {
        userRegisterService.usersList = ArrayList<User>()
    }

}