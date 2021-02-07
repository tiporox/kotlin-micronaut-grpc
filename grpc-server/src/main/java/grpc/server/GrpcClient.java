package grpc.server;

import helloworld.GreeterGrpc;
import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import io.micronaut.grpc.server.GrpcServerChannel;

@Factory
public class GrpcClient {
    @Bean
    GreeterGrpc.GreeterBlockingStub blockingStub(
        @GrpcChannel(GrpcServerChannel.NAME) ManagedChannel channel
    ) {
        return GreeterGrpc.newBlockingStub(
                channel
        );
    }
}
