package com.training.grpc.client.service;

import com.training.grpc.DummyMessage;
import com.training.grpc.UpperCaseServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

    private final UpperCaseServiceGrpc.UpperCaseServiceBlockingStub upperCaseServiceBlockingStub;

    public DummyService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        this.upperCaseServiceBlockingStub = UpperCaseServiceGrpc.newBlockingStub(channel);
    }

    public String dummyFunction(String message) {
        DummyMessage dummyMessage = DummyMessage.newBuilder().setMessage(message).build();
        return upperCaseServiceBlockingStub.upperCaseFunction(dummyMessage).getMessage();
    }

}
