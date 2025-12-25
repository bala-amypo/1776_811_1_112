package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import com.example.demo.service.CredentialHolderProfileService;

@Service
public class CredentialHolderProfileServiceImpl implements CredentialHolderProfileService {

    private final CredentialHolderProfileRepository repository;
    public CredentialHolderProfileServiceImpl(CredentialHolderProfileRepository repository) {
        this.repository = repository;
    }
    @Override
    public CredentialHolderProfile createHolder(CredentialHolderProfile profile) {
        return repository.save(profile);
    }

    @Override
    public CredentialHolderProfile getHolderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found"));
    }

    @Override
    public List<CredentialHolderProfile> getAllHolders() {
        return repository.findAll();
    }

    @Override
    public CredentialHolderProfile updateStatus(Long id, boolean active) {
        CredentialHolderProfile profile = getHolderById(id);
        profile.setActive(active);
        return repository.save(profile);
    }
}
