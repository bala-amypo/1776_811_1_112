package com.example.demo.repository;

import com.example.demo.entity.VerificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerificationRequestRepository
        extends JpaRepository<VerificationRequest, Long> {

    VerificationRequest save(VerificationRequest request);

    Optional<VerificationRequest> findById(Long id);

    List<VerificationRequest> findByCredentialId(Long credentialId);
}
