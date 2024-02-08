package user.models;

public class UserRequest {
    private String fullName;
    private String phoneNum;
    private String email;

    public UserRequest() { this(null, null, null); }
    public UserRequest(String fullName, String phoneNum, String email) {
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.email = email;
    }

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
