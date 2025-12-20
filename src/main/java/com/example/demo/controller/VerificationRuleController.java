package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.VerificationRule;
import com.example.demo.service.VerificationRuleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Verification Rules")
public class VerificationRuleController {

    private final VerificationRuleService service;

    public VerificationRuleController(VerificationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VerificationRule> create(
            @RequestBody VerificationRule rule) {
        return ResponseEntity.ok(service.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VerificationRule> update(
            @PathVariable Long id,
            @RequestBody VerificationRule updatedRule) {
        return ResponseEntity.ok(service.updateRule(id, updatedRule));
    }

    @GetMapping("/active")
    public ResponseEntity<List<VerificationRule>> getActiveRules() {
        return ResponseEntity.ok(service.getActiveRules());
    }

    @GetMapping
    public ResponseEntity<List<VerificationRule>> getAllRules() {
        return ResponseEntity.ok(service.getAllRules());
    }
}
