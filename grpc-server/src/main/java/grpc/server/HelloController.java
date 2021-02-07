package grpc.server;

import helloworld.GreeterGrpc;
import helloworld.HelloRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import javax.inject.Inject;

@Controller("/hello")
public class HelloController {

    @Inject
    GreeterGrpc.GreeterBlockingStub blockingStub;

    @Get("/{name}")
    public String sayHello(@PathVariable String name) {
        final HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return this.blockingStub.sayHello(request).getMessage();
    }

}
