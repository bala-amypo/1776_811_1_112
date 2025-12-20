package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.CredentialRecord;

public interface CredentialRecordService {

    CredentialRecord createCredential(CredentialRecord record);

    CredentialRecord updateCredential(Long id, CredentialRecord update);

    List<CredentialRecord> getCredentialsByHolder(Long holderId);

    CredentialRecord getCredentialByCode(String code);

    List<CredentialRecord> getAllCredentials();
}
