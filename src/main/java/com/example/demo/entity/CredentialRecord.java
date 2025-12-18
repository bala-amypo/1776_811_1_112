import java.time.LocalDate;
@Entity
public class CredentialRecord {
private long id;
private long holderid;
private String  credentialCode;
private String title;
private String issuer;
private LocalDate issueDate;
private LocalDate expiryDate;
private String credentialType;
private String status;
private String metadatajson;

public CredentialRecord(long id, long holderid, String credentialCode, String title, String issuer, LocalDate issueDate,
        LocalDate expiryDate, String credentialType, String status, String metadatajson) {
    this.id = id;
    this.holderid = holderid;
    this.credentialCode = credentialCode;
    this.title = title;
    this.issuer = issuer;
    this.issueDate = issueDate;
    this.expiryDate = expiryDate;
    this.credentialType = credentialType;
    this.status = status;
    this.metadatajson = metadatajson;
}
public void getId(){
    return id;
}
public int setId(){
 id=this.id;
}
public void setId(long id) {
    this.id = id;
}
public long getHolderid() {
    return holderid;
}
public void setHolderid(long holderid) {
    this.holderid = holderid;
}
public String getCredentialCode() {
    return credentialCode;
}
public void setCredentialCode(String credentialCode) {
    this.credentialCode = credentialCode;
}
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getIssuer() {
    return issuer;
}
public void setIssuer(String issuer) {
    this.issuer = issuer;
}
public LocalDate getIssueDate() {
    return issueDate;
}
public void setIssueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
}
public LocalDate getExpiryDate() {
    return expiryDate;
}
public void setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
}
public String getCredentialType() {
    return credentialType;
}
public void setCredentialType(String credentialType) {
    this.credentialType = credentialType;
}
public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}
public String getMetadatajson() {
    return metadatajson;
}
public void setMetadatajson(String metadatajson) {
    this.metadatajson = metadatajson;
}

}
