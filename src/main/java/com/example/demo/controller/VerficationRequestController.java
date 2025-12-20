package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/verification")
@Tag(name = "Verification Requests")
public class VerificationRequestController {

    private final VerificationRequestService service;

    public VerificationRequestController(VerificationRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VerificationRequest> initiate(
            @RequestBody VerificationRequest request) {
        return ResponseEntity.ok(service.initiateVerification(request));
    }

    @PutMapping("/{id}/process")
    public ResponseEntity<VerificationRequest> process(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.processVerification(id));
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<VerificationRequest>> getByCredential(
            @PathVariable Long credentialId) {
        return ResponseEntity.ok(service.getRequestsByCredential(credentialId));
    }

    @GetMapping
    public ResponseEntity<List<VerificationRequest>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }
}
