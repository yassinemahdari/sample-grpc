package com.training.grpc.server.config;

import com.training.grpc.server.service.UpperCaseService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class GrpcServerConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GrpcServerConfiguration.class);

    private Server server;
    private final UpperCaseService upperCaseService;

    public GrpcServerConfiguration(UpperCaseService upperCaseService) {
        this.upperCaseService = upperCaseService;
    }

    @PostConstruct
    private void initGrpcServer() {
        new Thread() {
            @Override
            public void run() {
                int port = 50051;
                server = ServerBuilder.forPort(port).addService(upperCaseService).build();
                try {
                    server.start();
                    logger.info("gRPC server started on port {}", port);
                    server.awaitTermination();
                } catch (IOException | InterruptedException e) {
                    logger.error("error starting gRPC server", e);
                    Thread.currentThread().interrupt();
                }
            }
        }.start();
    }

    @PreDestroy
    private void destroy() {
        if (server != null)
            server.shutdownNow();
    }


}
