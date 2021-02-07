package br.com.tiporox.users.listener

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import javax.inject.Singleton

@Singleton
class StartupListener : ApplicationEventListener<ServerStartupEvent> {

    override fun onApplicationEvent(event: ServerStartupEvent?) {
        println("StartupListener: executado")
    }
}