package br.com.tiporox.users.service

import br.com.tiporox.users.model.User
import io.micronaut.context.annotation.Primary
import io.micronaut.core.annotation.Order
import javax.inject.Singleton

//
//@Order(10)
//@Primary
//@Singleton
//class Exemplo1UserRegisterService : UserRegisterService {
//
//    override var count: Int = 10
//    override var usersList: MutableList<User> = ArrayList<User>()
//
//    override fun execute(id: String, name: String): User {
//        return User(id, name, 0, "")
//    }
//
//
//}