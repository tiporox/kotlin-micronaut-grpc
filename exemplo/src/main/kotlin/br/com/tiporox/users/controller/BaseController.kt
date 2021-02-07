package br.com.tiporox.users.controller

import com.fasterxml.jackson.core.JsonParseException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link

open class BaseController {


    @Error
    fun jsonError(request: HttpRequest<*>, jsonParseException: JsonParseException): HttpResponse<JsonError> {
        val error = JsonError("Invalid JSON: " + jsonParseException.message)
            .link(Link.SELF, Link.of(request.uri))

        return HttpResponse.status<JsonError>(HttpStatus.BAD_REQUEST, "Fix Your JSON")

            .body(error)
    }

    @Error(global = true)
    fun error(request: HttpRequest<*>, e: Throwable): HttpResponse<JsonError> {
        val error = JsonError("Bad Things Happened: " + e.message)
            .link(Link.SELF, Link.of(request.uri))

        return HttpResponse.serverError<JsonError>()
            .body(error)
    }


}