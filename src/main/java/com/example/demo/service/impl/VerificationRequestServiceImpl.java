package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.CredentialRecordService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordService credentialService; // ✅ SERVICE
    private final AuditTrailService auditTrailService;
    private final VerificationRuleService verificationRuleService;

    // 🔴 ORDER MUST MATCH TEST
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordService credentialService,
            AuditTrailService auditTrailService,
            VerificationRuleService verificationRuleService) {

        this.requestRepo = requestRepo;
        this.credentialService = credentialService;
        this.auditTrailService = auditTrailService;
        this.verificationRuleService = verificationRuleService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));

        CredentialRecord credential =
                credentialService.getAllCredentials().stream()
                        .filter(c -> c.getId().equals(request.getCredentialId()))
                        .findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        if (credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {
            request.setStatus("EXPIRED"); // 🔴 TEST EXPECTS THIS
        } else {
            request.setStatus("SUCCESS");
        }

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        auditTrailService.logEvent(audit);

        return requestRepo.save(request);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }

    @Override
    public List<VerificationRequest> getAllRequests() {
        return requestRepo.findAll();
    }
}
