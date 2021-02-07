package br.com.tiporox.users.filter

import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.HttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import org.reactivestreams.Publisher

@Filter("/users/**")
class TraceFilter(private val traceService: TraceService) : HttpServerFilter {

    override fun doFilter(request: HttpRequest<*>, chain: ServerFilterChain): Publisher<MutableHttpResponse<*>> {
        return traceService.trace(request)
            .switchMap { aBoolean -> chain.proceed(request) }
            .doOnNext { res ->
                res.headers.add("X-Trace-Enabled", "true")
            }
    }

}