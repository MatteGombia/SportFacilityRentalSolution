package user.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import user.models.User;
import user.models.UserRequest;
import user.models.UserResponse;
import user.services.UserService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UserService userMockService;

    @Test
    public void testCreateValidUser() throws Exception {

        UserRequest userRequest = new UserRequest("Joe Frog", "666 777 333", "frog@example.com");
        UserResponse expectedUserResponse =new UserResponse(1L,"Joe Frog",
                "666 777 333", "frog@example.com");


        String expectedResponseBody = om.writeValueAsString(expectedUserResponse);

        String endpoint = "/users";

        User serviceUser = new User(1L,"Joe Frog",
                "666 777 333", "frog@example.com");


        when(userMockService.saveUser(any(User.class))).thenReturn(serviceUser);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, userRequest, String.class);

        verify(userMockService, times(1)).saveUser(any(User.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }

    @Test
    public void testGetOneValidUser() throws JsonProcessingException {
        UserResponse expectedUserResponse =new UserResponse(1L,"Joe Frog",
                "666 777 333", "frog@example.com");

        String expectedResponseBody = om.writeValueAsString(expectedUserResponse);

        Long userId = 1L;

        String endpoint = "/users/" + userId;

        User serviceUser = new User(1L,"Joe Frog",
                "666 777 333", "frog@example.com");

        when(userMockService.getUserById(any(Long.class))).thenReturn(serviceUser);

        ResponseEntity<String> responseEntity =
                testRestTemplate.getForEntity(endpoint, String.class);

        verify(userMockService, times(1)).getUserById(any(Long.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertThat(responseEntity.getBody()).isEqualTo(expectedResponseBody);
    }

    @Test
    public void testGetAllValidUsers() throws JsonProcessingException {

        List<User> mockUsers = new ArrayList<>();

        String endpoint = "/users";

        User mockUser_1 = new User(1L,"Joe Frog",
                "666 777 333", "frog@example.com");
        User mockUser_2 = new User(2L,"Bob Cat",
                "333 222 111", "cat@example.com");

        List<String> users = new ArrayList<>();

        mockUsers.add(mockUser_1);
        mockUsers.add(mockUser_2);

        for(User user : mockUsers) {
            users.add(om.writeValueAsString(user));
        }

        when(userMockService.getAllUsers()).thenReturn(mockUsers);

        ResponseEntity<List<User>> responseEntity = testRestTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {});


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<String> responseUsers = new ArrayList<>();

        for(User user : responseEntity.getBody()) {
            responseUsers.add(om.writeValueAsString(user));
        }

        assertThat(responseUsers).isEqualTo(users);
    }

    @Test
    public void testDeleteValidUser() {
        Long userId = 1L;

        String endpoint = "/users/" + userId;

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                endpoint,
                HttpMethod.DELETE,
                null,
                String.class,
                userId);

        verify(userMockService, times(1)).deleteUserById(any(Long.class));

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        assertNull(responseEntity.getBody());
    }

}
