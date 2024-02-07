package user.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String phoneNum;
    private String email;

    public UserEntity() {}

    public UserEntity(Long id, String fullName, String phoneNum, String email) {
        this.setId(id);
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
