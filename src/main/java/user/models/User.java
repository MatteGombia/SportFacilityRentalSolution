package user.models;

public class User {
    private Long id;
    private String fullName;
    private String phoneNum;
    private String email;

    public User() { this(null, null, null, null); }

    public User(String fullName, String phoneNum, String email) {
        this(null, fullName, phoneNum, email);
    }

    public User(Long id, String fullName, String phoneNum, String email) {
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

