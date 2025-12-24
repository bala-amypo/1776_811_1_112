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
    private final CredentialRecordService credentialService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditTrailService;

    // 🔴 EXACT constructor required by test
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditTrailService) {

        this.requestRepo = requestRepo;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditTrailService = auditTrailService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found"));

        CredentialRecord credential = credentialService.getCredentialByCode(
                String.valueOf(request.getCredentialId())
        );

        if (credential != null &&
                credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {

            request.setStatus("FAILED");
            request.setResultMessage("Credential expired");

        } else {
            request.setStatus("SUCCESS");
            request.setResultMessage("Credential valid");
        }

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
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
