import java.time.LocalDateTime;

public class User {
 @Column(unique=true)
 private long id;
 private String holderid;
 private String fullname;
@Column(unique=true)
 private String email;
 private String oranganization;
 private boolean active;
 private LocalDateTime createdAt;

 public User(long id, String holderid, String fullname, String email, String oranganization, boolean active,
        LocalDateTime createdAt) {
    this.id = id;
    this.holderid = holderid;
    this.fullname = fullname;
    this.email = email;
    this.oranganization = oranganization;
    this.active = active;
    this.createdAt = createdAt;
}
 public  void setId(long id){
    id=this.id;
 }
 public long getid(long id){
     return id;
     
 }
 public long getId() {
    return id;
 }
 public String getHolderid() {
    return holderid;
 }
 public void setHolderid(String holderid) {
    this.holderid = holderid;
 }
 public String getFullname() {
    return fullname;
 }
 public void setFullname(String fullname) {
    this.fullname = fullname;
 }
 public String getEmail() {
    return email;
 }
 public void setEmail(String email) {
    this.email = email;
 }
 public String getOranganization() {
    return oranganization;
 }
 public void setOranganization(String oranganization) {
    this.oranganization = oranganization;
 }
 public boolean isActive() {
    return active;
 }
 public void setActive(boolean active) {
    this.active = active;
 }
 public LocalDateTime getCreatedAt() {
    return createdAt;
 }
 public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
 }
}
