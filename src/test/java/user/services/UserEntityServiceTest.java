package user.services;

import user.models.User;
import user.models.UserEntity;
import user.models.UserRequest;
import user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
@ActiveProfiles("test")
public class UserEntityServiceTest {

    @MockBean
    UserRepository userMockRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testSaveUser() {
        User userToBeSaved = new User("Joe Frog", "666 777 333", "frog@example.com");
        User expectedSavedUser = new User(1L,"Joe Frog", "666 777 333", "frog@example.com");
        UserEntity outputUserEntity = new UserEntity(1L,"Joe Frog", "666 777 333",
                "frog@example.com");

        when(userMockRepository.save(any(UserEntity.class))).thenReturn(outputUserEntity);

        User savedUser = userService.saveUser(userToBeSaved);

        assertThat(savedUser).isEqualToComparingFieldByField(expectedSavedUser);

        verify(userMockRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testFindUserById() {

        User expectedUser = new User(1L,"Joe Frog", "666 777 333", "frog@example.com");

        UserEntity outputUserEntity = new UserEntity(1L,"Joe Frog", "666 777 333",
                "frog@example.com");

        when(userMockRepository.getOne(any(Long.class))).thenReturn(outputUserEntity);

        User dbUser = userService.getUserById(1L);

        assertThat(dbUser).isEqualToComparingFieldByField(expectedUser);

        verify(userMockRepository, times(1)).getOne(any(Long.class));
    }

    @Test
    public void testFindAllUsers() {

        UserEntity mockUser_1 = new UserEntity(1L, "Joe Frog", "666 777 333",
                "frog@example.com");
        UserEntity mockUser_2 = new UserEntity(2L, "Bob Cat", "333 222 111",
                "cat@example.com");

        List<UserEntity> outputUserEntities = new ArrayList<>();

        outputUserEntities.add(mockUser_1);
        outputUserEntities.add(mockUser_2);

        when(userMockRepository.findAll()).thenReturn(outputUserEntities);

        List<User> dbUsers = userService.getAllUsers();

        for(int i = 0; i < dbUsers.size(); i++) {
            assertThat(dbUsers.get(i)).isEqualToComparingFieldByField(outputUserEntities.get(i));
        }

        verify(userMockRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateUserById() {
        UserRequest userRequest = new UserRequest("Joe Frog", "666 777 333", "frog@example.com");
        User expectedUser = new User(1L, "Joe Frog", "666 777 333", "frog@example.com");
        UserEntity outputUser = new UserEntity(1L, "Joe Frog", "666 777 333",
                "frog@example.com");

        when(userMockRepository.getOne(any(Long.class))).thenReturn(outputUser);
        when(userMockRepository.save(any(UserEntity.class))).thenReturn(outputUser);

        User updatedUser = userService.updateUser(1L, userRequest);

        verify(userMockRepository, times(1)).save(any(UserEntity.class));
        verify(userMockRepository, times(1)).getOne(any(Long.class));

        assertThat(updatedUser).isEqualToComparingFieldByField(expectedUser);
    }

    @Test
    public void testDeleteUserById() {

        Long userId = 1L;

        doNothing().when(userMockRepository).deleteById(any(Long.class));

        userService.deleteUserById(userId);

        verify(userMockRepository, times(1)).deleteById(any(Long.class));
    }

}
