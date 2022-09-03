package com.training.grpc.server.service;

import com.training.grpc.DummyMessage;
import com.training.grpc.UpperCaseServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class UpperCaseService extends UpperCaseServiceGrpc.UpperCaseServiceImplBase {

    @Override
    public void upperCaseFunction(DummyMessage request, StreamObserver<DummyMessage> responseObserver) {
        responseObserver.onNext(DummyMessage.newBuilder().setMessage(request.getMessage().toUpperCase()).build());
        responseObserver.onCompleted();
    }
}
