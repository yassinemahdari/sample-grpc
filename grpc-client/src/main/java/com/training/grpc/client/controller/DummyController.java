package com.training.grpc.client.controller;

import com.training.grpc.client.service.DummyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    private final DummyService dummyService;

    public DummyController(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @GetMapping
    public String dummyFunction(@RequestParam String message) {
        return dummyService.dummyFunction(message);
    }
}
