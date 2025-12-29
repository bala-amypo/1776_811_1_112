package com.example.demo.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "credential_record")
public class CredentialRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private Long holderId;
    @Column(nullable = false)
    private String credentialCode;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String issuer;
    @Column(nullable = false)
    private LocalDate issueDate;
    @Column(nullable = false)
    private LocalDate expiryDate;
    @Column(nullable = false)
    private String credentialType;
    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String metadataJson;

    @ManyToMany
    @JoinTable(
        name = "credential_rule_map",
        joinColumns = @JoinColumn(name = "credential_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<VerificationRule> rules = new HashSet<>();

    /* ---------------- SAFE RULE METHODS ---------------- */

    public Set<VerificationRule> getRules() {
        if (rules == null) {
            rules = new HashSet<>();
        }
        return rules;
    }

    public void setRules(Set<VerificationRule> rules) {
        this.rules = (rules == null) ? new HashSet<>() : rules;
    }

    public void addRule(VerificationRule rule) {
        if (rule != null) {
            getRules().add(rule);
        }
    }

    public void removeRule(VerificationRule rule) {
        if (rule != null) {
            getRules().remove(rule);
        }
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getHolderId() { return holderId; }
    public void setHolderId(Long holderId) { this.holderId = holderId; }

    public String getCredentialCode() { return credentialCode; }
    public void setCredentialCode(String credentialCode) { this.credentialCode = credentialCode; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getCredentialType() { return credentialType; }
    public void setCredentialType(String credentialType) { this.credentialType = credentialType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMetadataJson() { return metadataJson; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
}
