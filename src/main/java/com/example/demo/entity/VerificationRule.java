package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleCode;

    private Boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // ✅ REQUIRED
        this.id = id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // ✅ IMPORTANT FOR SET BEHAVIOR
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerificationRule)) return false;
        VerificationRule that = (VerificationRule) o;
        return ruleCode != null && ruleCode.equals(that.ruleCode);
    }

    @Override
    public int hashCode() {
        return ruleCode != null ? ruleCode.hashCode() : 0;
    }
}
