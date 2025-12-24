@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final AuditTrailService auditTrailService;

    // 🔴 EXACT constructor order required by tests
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo,
            AuditTrailService auditTrailService) {

        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
        this.auditTrailService = auditTrailService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found"));

        CredentialRecord credential = credentialRepo.findById(request.getCredentialId())
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        // 🔴 TEST EXPECTS FAILED IF EXPIRED
        if (credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {

            request.setStatus("FAILED");
        } else {
            request.setStatus("SUCCESS");
        }

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        auditTrailService.logEvent(audit);

        return requestRepo.save(request);
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }

    @Override
    public List<VerificationRequest> getAllRequests() {
        return requestRepo.findAll();
    }
}
