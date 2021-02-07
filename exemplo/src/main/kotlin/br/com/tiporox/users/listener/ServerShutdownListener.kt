package br.com.tiporox.users.listener

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerShutdownEvent
import javax.inject.Singleton

@Singleton
class ServerShutdownListener : ApplicationEventListener<ServerShutdownEvent> {

    override fun onApplicationEvent(event: ServerShutdownEvent?) {
        println("ServerShutdownEvent: executado")
    }
}