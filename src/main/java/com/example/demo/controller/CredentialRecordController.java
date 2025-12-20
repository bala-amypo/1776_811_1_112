package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/credentials")
@Tag(name = "Credential Records")
public class CredentialRecordController {

    private final CredentialRecordService service;

    public CredentialRecordController(CredentialRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CredentialRecord> create(
            @RequestBody CredentialRecord record) {
        return ResponseEntity.ok(service.createCredential(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredentialRecord> update(
            @PathVariable Long id,
            @RequestBody CredentialRecord update) {
        return ResponseEntity.ok(service.updateCredential(id, update));
    }

    @GetMapping("/holder/{holderId}")
    public ResponseEntity<List<CredentialRecord>> getByHolder(
            @PathVariable Long holderId) {
        return ResponseEntity.ok(service.getCredentialsByHolder(holderId));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CredentialRecord> getByCode(
            @PathVariable String code) {
        return ResponseEntity.ok(service.getCredentialByCode(code));
    }

    @GetMapping
    public ResponseEntity<List<CredentialRecord>> getAll() {
        return ResponseEntity.ok(service.getAllCredentials());
    }
}
