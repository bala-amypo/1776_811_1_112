package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditTrailService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo,
            VerificationRuleService ruleService,
            AuditTrailService auditTrailService) {

        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
        this.ruleService = ruleService;
        this.auditTrailService = auditTrailService;
    }

    // --------------------------------------------------

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        return requestRepo.save(request);
    }

    // --------------------------------------------------

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification request not found"));

        List<CredentialRecord> credentials = credentialRepo.findAll();

        boolean expired = credentials.stream()
                .filter(c -> c.getId().equals(request.getCredentialId()))
                .anyMatch(c ->
                        c.getExpiryDate() != null &&
                        c.getExpiryDate().isBefore(LocalDate.now())
                );

        request.setStatus(expired ? "FAILED" : "SUCCESS");
        VerificationRequest saved = requestRepo.save(request);

        // Audit log (ONLY fields that exist)
        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        auditTrailService.logEvent(audit);

        return saved;
    }

    // --------------------------------------------------

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }

    // --------------------------------------------------

    @Override
    public List<VerificationRequest> getAllRequests() {
        return requestRepo.findAll();
    }
}
