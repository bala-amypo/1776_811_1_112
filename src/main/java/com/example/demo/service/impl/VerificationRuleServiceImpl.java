package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRule;
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
    public VerificationRule updateRule(Long id, VerificationRule rule) {
        VerificationRule existing = ruleRepository.findById(id).orElseThrow();
        existing.setRuleCode(rule.getRuleCode());
        existing.setDescription(rule.getDescription());
        existing.setAppliesToType(rule.getAppliesToType());
        existing.setValidationExpression(rule.getValidationExpression());
        existing.setActive(rule.getActive());
        return ruleRepository.save(existing);
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    // ✅ THIS FIXES THE ERROR
    @Override
    public List<VerificationRule> getActiveRules() {
        return ruleRepository.findByActive(true);
    }
}
