@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    public CredentialRecordServiceImpl(CredentialRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {

        if (record.getStatus() == null) {
            record.setStatus("ACTIVE");
        }

        if (record.getExpiryDate() != null &&
            record.getExpiryDate().isBefore(LocalDate.now())) {
            record.setStatus("EXPIRED");
        }

        return repository.save(record);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord update) {
        CredentialRecord existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        // ❌ DO NOT update credentialCode (unique & immutable)
        existing.setTitle(update.getTitle());
        existing.setIssuer(update.getIssuer());
        existing.setStatus(update.getStatus());
        existing.setExpiryDate(update.getExpiryDate());
        existing.setMetadataJson(update.getMetadataJson());

        return repository.save(existing);
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return repository.findByCredentialCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }
}
