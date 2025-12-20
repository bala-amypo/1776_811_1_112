package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.VerificationRequest;

public interface VerificationRequestService {

    VerificationRequest initiateVerification(VerificationRequest request);

    VerificationRequest processVerification(Long requestId);

    List<VerificationRequest> getRequestsByCredential(Long credentialId);

    List<VerificationRequest> getAllRequests();
}
