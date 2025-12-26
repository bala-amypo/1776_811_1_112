package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VerificationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRuleServiceImpl
        implements VerificationRuleService {

    private final VerificationRuleRepository ruleRepo;

    public VerificationRuleServiceImpl(VerificationRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return ruleRepo.save(rule);
    }

    // ⭐ THIS METHOD FIXES THE ERROR
    @Override
    public VerificationRule updateRule(Long id, VerificationRule rule) {

        VerificationRule existing = ruleRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));

        existing.setRuleCode(rule.getRuleCode());
        existing.setDescription(rule.getDescription());
        existing.setValidationExpression(rule.getValidationExpression());
        existing.setAppliesToType(rule.getAppliesToType());
        existing.setActive(rule.getActive());

        return ruleRepo.save(existing);
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return ruleRepo.findAll();
    }

    @Override
    public List<VerificationRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }
}
