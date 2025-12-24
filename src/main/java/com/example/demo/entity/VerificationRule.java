package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "verification_rule")
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String description;
    private String appliesToType;
    private String validationExpression;
    private Boolean active = true;

    // -------- GETTERS & SETTERS --------

    public Long getId() {
        return id;
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

    // -------- REQUIRED FOR TESTS --------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerificationRule)) return false;
        VerificationRule rule = (VerificationRule) o;
        return ruleCode != null && ruleCode.equals(rule.ruleCode);
    }

    @Override
    public int hashCode() {
        return ruleCode != null ? ruleCode.hashCode() : 0;
    }
}
