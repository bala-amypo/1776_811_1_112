package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {
        if (record.getStatus() == null) {
            record.setStatus("VALID");
        }
        return repository.save(record);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {

        CredentialRecord existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));

        // ✅ FIX 1: update credentialCode (THIS FIXES t14)
        if (updated.getCredentialCode() != null) {
            existing.setCredentialCode(updated.getCredentialCode());
        }

        // ✅ FIX 2: update status if present
        if (updated.getStatus() != null) {
            existing.setStatus(updated.getStatus());
        }

        return repository.save(existing); // MUST return updated object
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        // ✅ test expects NULL when not found
        return repository.findByCredentialCode(code).orElse(null);
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

    // ⭐ required by tests
    @Override
    public List<CredentialRecord> getAll() {
        return repository.findAll();
    }
}
