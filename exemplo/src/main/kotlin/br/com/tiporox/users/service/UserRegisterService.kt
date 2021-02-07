package br.com.tiporox.users.service

import br.com.tiporox.users.model.User

interface UserRegisterService {

    var usersList: MutableList<User>;

    fun execute(id: String, name: String): User
    fun execute(user: User): User

}