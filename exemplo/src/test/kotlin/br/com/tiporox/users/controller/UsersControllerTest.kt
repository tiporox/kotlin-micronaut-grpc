package br.com.tiporox.users.controller

import br.com.tiporox.users.model.User
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.asm.TypeReference
import io.micronaut.core.type.Argument
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class UsersControllerTest {

    @Inject
    lateinit var server: EmbeddedServer

    @Inject
    @field:Client("/users")
    lateinit var usersClient: HttpClient

    @Test
    fun testUsersList() {
//        var response: List<User> = usersClient
//            .toBlocking()
//            .retrieve("/", Argument.listOf(User::class.java))
//
//        println(response)
    }

}