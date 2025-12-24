package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.VerificationRule;

public interface VerificationRuleRepository
        extends JpaRepository<VerificationRule, Long> {

    // 🔴 REQUIRED BY TEST
    List<VerificationRule> findByActiveTrue();
}
