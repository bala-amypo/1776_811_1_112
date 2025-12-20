package com.example.demo.repository;

import com.example.demo.entity.CredentialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    CredentialRecord save(CredentialRecord record);

    Optional<CredentialRecord> findById(Long id);

    List<CredentialRecord> findByHolderId(Long holderId);

    Optional<CredentialRecord> findByCredentialCode(String credentialCode);

    @Query("SELECT c FROM CredentialRecord c WHERE c.expiryDate < :date")
    List<CredentialRecord> findExpiredBefore(LocalDate date);

    @Query("SELECT c FROM CredentialRecord c WHERE c.status = :status")
    List<CredentialRecord> findByStatusUsingHql(String status);

    @Query("SELECT c FROM CredentialRecord c WHERE c.issuer = :issuer AND c.credentialType = :credentialType")
    List<CredentialRecord> searchByIssuerAndType(String issuer, String credentialType);
}
