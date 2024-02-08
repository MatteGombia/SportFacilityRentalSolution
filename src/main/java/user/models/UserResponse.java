package user.models;

public class UserResponse extends UserRequest {

    private Long id;

    public UserResponse() { this(null, null, null, null); }

    public UserResponse(Long id, String fullName, String phoneNum, String email) {
        super(fullName, phoneNum, email);
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

   }
