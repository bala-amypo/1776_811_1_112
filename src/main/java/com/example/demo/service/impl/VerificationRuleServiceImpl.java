package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {

    private final VerificationRuleRepository ruleRepository;

    public VerificationRuleServiceImpl(VerificationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public VerificationRule updateRule(Long id, VerificationRule updatedRule) {
        VerificationRule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        rule.setRuleCode(updatedRule.getRuleCode());
        rule.setActive(updatedRule.isActive()); // ✅ FIXED

        return ruleRepository.save(rule);
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
