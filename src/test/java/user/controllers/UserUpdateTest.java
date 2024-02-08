package user.controllers;

import user.models.User;
import user.models.UserRequest;
import user.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserUpdateTest {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userMockService;

    @Test
    public void testUpdateUser() throws Exception {
        Long userId = 1L;

        String endpoint = "/users/" + userId;

        UserRequest userRequest = new UserRequest("Joe Frog", "666 777 333", "frog@example.com");
        User expectedUser = new User(1L, "Joe Frog", "666 777 333", "frog@example.com");

        String update = om.writeValueAsString(userRequest);

        User serviceUser = new User(userId, "Joe Frog", "666 777 333", "frog@example.com");

        when(userMockService.updateUser(any(Long.class), any(UserRequest.class))).thenReturn(serviceUser);

        ResultActions result = mockMvc.perform(put(endpoint)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(update)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Joe Frog"))
                .andExpect(jsonPath("$.phoneNum").value("666 777 333"))
                .andExpect(jsonPath("$.email").value("frog@example.com"));

        verify(userMockService, times(1)).updateUser(any(Long.class), any(UserRequest.class));
    }
}
