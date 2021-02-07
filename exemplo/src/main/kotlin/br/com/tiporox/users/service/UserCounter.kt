package br.com.tiporox.users.service

import io.micronaut.core.annotation.Order
import io.micronaut.runtime.context.scope.Refreshable
import javax.annotation.PostConstruct

@Order(10)
@Refreshable
class UserCounter : Counter {

    override var count: Int = 0;


    override fun getValue(): Int {
        return count
    }

    override fun increment(): Int {

        count++
        return count
    }

    @PostConstruct
    fun init() {
        count = 0
    }

}