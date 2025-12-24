package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.VerificationRequest;

public interface VerificationRequestRepository
        extends JpaRepository<VerificationRequest, Long> {

    List<VerificationRequest> findByCredentialId(Long credentialId);
}
