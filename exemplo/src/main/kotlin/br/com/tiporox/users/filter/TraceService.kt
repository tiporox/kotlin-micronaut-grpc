package br.com.tiporox.users.filter

import io.micronaut.http.HttpRequest
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class TraceService {

    private val LOG = LoggerFactory.getLogger(TraceFilter::class.java)


    internal fun trace(request: HttpRequest<*>): Flowable<Boolean> {
        return Flowable.fromCallable {

            LOG.debug("Tracing request: " + request.uri)
            println("Tracing request: " + request.method + ": " + request.uri)
            // trace logic here, potentially performing I/O
            true
        }.subscribeOn(Schedulers.io())
    }

}