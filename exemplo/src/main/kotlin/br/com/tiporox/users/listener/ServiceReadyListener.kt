package br.com.tiporox.users.listener

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceReadyEvent
import javax.inject.Singleton

@Singleton
class ServiceReadyListener : ApplicationEventListener<ServiceReadyEvent> {

    override fun onApplicationEvent(event: ServiceReadyEvent?) {
        println("ServiceReadyEvent: executado")
    }
}