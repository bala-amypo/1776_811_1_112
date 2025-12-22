@Service
public class VerificationRuleServiceImpl implements VerificationRuleService {

    private final VerificationRuleRepository repository;

    public VerificationRuleServiceImpl(VerificationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public VerificationRule createRule(VerificationRule rule) {
        return repository.save(rule);
    }

    @Override
    public VerificationRule updateRule(Long id, VerificationRule updatedRule) {
        VerificationRule existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        existing.setRuleCode(updatedRule.getRuleCode());
        existing.setDescription(updatedRule.getDescription());
        existing.setAppliesToType(updatedRule.getAppliesToType());
        existing.setValidationExpression(updatedRule.getValidationExpression());
        existing.setActive(updatedRule.getActive());

        return repository.save(existing);
    }

    @Override
    public List<VerificationRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public List<VerificationRule> getAllRules() {
        return repository.findAll();
    }
}
