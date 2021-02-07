package br.com.tiporox.index.controller

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class IndexControllerTest {

    //lateinit permite criar propriedades sem inicializar na declaracao, nao é nulllable,
    //caso nao seja iniciado dispara exception
    @Inject
    lateinit var server: EmbeddedServer

    //@fieeld altera o bytecode gerado, para ter um atributo com a anotacao passada
    // https://americanexpress.io/advanced-kotlin-use-site-targets/
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun testIndexPath() {
        val rsp: String = client.toBlocking()
            .retrieve("/")
        Assertions.assertEquals("Hello world", rsp)
    }

}