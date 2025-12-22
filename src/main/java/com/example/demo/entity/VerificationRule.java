@Entity
@Table(name = "verification_rule")
public class VerificationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String description;
    private String appliesToType;
    private String validationExpression;
    private Boolean active;

    // getters & setters
}
