package user.services;

import user.models.User;
import user.models.UserRequest;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, UserRequest userRequest);
    void deleteUserById(Long id);
}
