package user.services;

import user.models.User;
import user.models.UserEntity;
import user.models.UserRequest;
import user.repositories.UserRepository;
import user.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User saveUser(User user) {

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        userEntity = userRepository.save(userEntity);

        User savedUser = modelMapper.map(userEntity, User.class);

        return savedUser;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.getOne(id);

        User user = modelMapper.map(userEntity, User.class);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> dbUsers = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();

        for(int i = 0; i < userEntities.size(); i++) {
            UserEntity userEntity = userEntities.get(i);
            dbUsers.add(modelMapper.map(userEntity, User.class));
        }
        return dbUsers;
    }

    @Override
    public User updateUser(Long id, UserRequest user) {
        User dbUser = getUserById(id);

        dbUser.setId(id);
        dbUser.setFullName(user.getFullName());
        dbUser.setPhoneNum(user.getPhoneNum());
        dbUser.setEmail(user.getEmail());

        dbUser = saveUser(dbUser);

        return dbUser;
    }

    @Override
    public void deleteUserById(Long id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            throw new EntityNotFoundException("Couldn't resolve ID: " + id);
        }
    }
}
