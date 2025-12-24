package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; } // ✅ REQUIRED BY TESTS

    public Long getCredentialId() { return credentialId; }
    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
