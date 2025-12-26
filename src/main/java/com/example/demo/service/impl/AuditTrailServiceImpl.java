package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.service.AuditTrailService;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRecordRepository auditRepo;

    public AuditTrailServiceImpl(AuditTrailRecordRepository auditRepo) {
        this.auditRepo = auditRepo;
    }

    @Override
    public AuditTrailRecord logEvent(AuditTrailRecord record) {
        if (record.getLoggedAt() == null) {
            record.setLoggedAt(LocalDateTime.now());
        }
        return auditRepo.save(record);
    }

    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        return auditRepo.findByCredentialId(credentialId);
    }

    // ⭐ REQUIRED METHOD (THIS FIXES THE ERROR)
    @Override
    public List<AuditTrailRecord> getAllLogs() {
        return auditRepo.findAll();
    }
}
