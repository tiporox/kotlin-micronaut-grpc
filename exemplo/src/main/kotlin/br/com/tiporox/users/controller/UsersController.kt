package br.com.tiporox.users.controller

import br.com.tiporox.users.annotation.RequireToken
import br.com.tiporox.users.model.User
import br.com.tiporox.users.service.UserCounter
import br.com.tiporox.users.service.UserService
import com.fasterxml.jackson.core.JsonLocation
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import io.micronaut.context.ApplicationContext
import io.micronaut.core.version.annotation.Version
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.runtime.context.scope.refresh.RefreshEvent
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/users", produces = [MediaType.APPLICATION_JSON])
class UsersController : BaseController() {

    @Inject
    lateinit var userService: UserService;

    @Inject
    lateinit var userCounter: UserCounter;

    @Inject
    lateinit var context: ApplicationContext

    @Get(uris = ["", "/{id}"])
    fun list(@PathVariable id: String?): HttpResponse<*> {

        return if (id != null) {
            val userList = userService.list().filter { user -> user.id == id }
            if(userList.isEmpty()) {
                return HttpResponse.notFound("")
            }
            return HttpResponse.ok(userList.first())
        } else {
            HttpResponse.ok(userService.list())
        }

//        context.publishEvent(RefreshEvent())
//        var usersList = ArrayList<User>()
//        usersList.add(User("1", "Jose", 29, "M"))
//        usersList.add(User("2","Wal", 25, "F"))
//
//        val introspection = BeanIntrospection.getIntrospection(User::class.java)
//        var user: User = introspection.instantiate("3", "Hugo", 35, "M")
//
//        introspection.beanProperties.forEach {
//            println("prop: $it")
//        }
//
//        usersList.add(user);

    }

    @Get("/refresh")
    fun resetCounter() {
        context.publishEvent(RefreshEvent())
    }

    @Version("1")
    @Post
    fun create(@Body @Valid user: User): HttpResponse<*> {
        user.id = userCounter.increment().toString()
        return HttpResponse.created(userService.register(user))
    }

    @RequireToken
    @Version("2")
    @Post
    fun createV2(@Body @Valid user: User): HttpResponse<*> {
        user.id = userCounter.increment().toString()
        user.name = user.name.toString().toUpperCase()
        return HttpResponse.created(userService.register(user))
    }

    @Put
    fun testeError() {
        throw Exception("Teste")
    }

    @Patch
    fun testeErrorEspecifico() {
        throw JsonParseException("", null)
    }

}