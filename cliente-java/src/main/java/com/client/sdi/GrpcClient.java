package com.client.sdi;

import com.proto.sdi.GreeterGrpc;
import com.proto.sdi.HelloReply;
import com.proto.sdi.HelloRequest;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GrpcClient {
    private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());

    public static void main(String[] args) throws Exception {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        try {
            Metadata metadata = new Metadata();
            metadata.put(Metadata.Key.of("authorization",
                            Metadata.ASCII_STRING_MARSHALLER),
                    "Bearer abc123xyz789");
            metadata.put(Metadata.Key.of("client-version",
                            Metadata.ASCII_STRING_MARSHALLER),
                    "1.0.0-java");

            GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel)
                    .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));

            HelloRequest request = HelloRequest.newBuilder()
                    .setName("Mundo")
                    .build();

            HelloReply response = stub.sayHello(request);

            System.out.println("Respuesta del servidor: " + response.getMessage());

        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
