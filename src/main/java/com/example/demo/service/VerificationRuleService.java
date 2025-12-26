package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.VerificationRule;

public interface VerificationRuleService {

    VerificationRule createRule(VerificationRule rule);

    VerificationRule updateRule(Long id, VerificationRule rule);

    List<VerificationRule> getAllRules();

    List<VerificationRule> getActiveRules();
}
