package user.controllers;

import user.models.User;
import user.models.UserRequest;
import user.models.UserResponse;
import user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse newUser(@RequestBody UserRequest userRequest) {

        User user = modelMapper.map(userRequest, User.class);

        User savedUser = userService.saveUser(user);

        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);

        return userResponse;
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse findOne(@PathVariable Long id) {
        User dbUser = userService.getUserById(id);

        UserResponse userResponse = modelMapper.map(dbUser, UserResponse.class);

        return userResponse;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    List<UserResponse> FindAll() {
        List<UserResponse> userResponses = new ArrayList<>();

        List<User> users = userService.getAllUsers();

        for(User user : users) {
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        User savedUser = userService.updateUser(id, userRequest);

        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);

        return userResponse;
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
