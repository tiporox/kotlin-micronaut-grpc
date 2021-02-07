package br.com.tiporox.index.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/")
class IndexController {

    @Get
    fun index(): String {
        return "Hello world"
    }

}