package com.rafaelpavan.services.user;

import com.rafaelpavan.exceptions.user.*;
import com.rafaelpavan.models.dtos.user.UpdateUserDto;
import com.rafaelpavan.models.dtos.user.UserDto;
import com.rafaelpavan.models.dtos.validations.ErrorDto;
import com.rafaelpavan.models.entities.user.User;
import com.rafaelpavan.models.enums.user.UserType;
import com.rafaelpavan.models.mapper.user.UpdateUserMapper;
import com.rafaelpavan.repositories.user.UserRepository;
import com.rafaelpavan.validations.user.SaveUserValidation;
import com.rafaelpavan.validations.user.UpdateUserValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UpdateUserMapper userMapper;


    public UserService(UserRepository userRepository, UpdateUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User saveUser(UserDto data) {
        var newUser = new User(data);

        List<ErrorDto> errors = SaveUserValidation.execute(data);

        if (!errors.isEmpty()) {
            throw new BlankOrNullFieldsException(errors);
        }

        if (this.userRepository.existsByDocument(newUser.getDocument())) {
            throw new DuplicateDcoException();
        }
        if (this.userRepository.existsByEmail(newUser.getEmail())) {
            throw new DuplicateEmailException();
        }

        this.userRepository.save(newUser);

        return newUser;
    }


    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserById(UUID id) {
        return this.userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
    }
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public User updateUser(UUID id, UpdateUserDto updateUserDto) {

        var findUser = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);

        var updateUser = userMapper.toEntity(updateUserDto);
        updateUser.setId(findUser.getId());
        updateUser.setPassword(findUser.getPassword());
        updateUser.setDocument(findUser.getDocument());
        updateUser.setBalance(findUser.getBalance());

        List<ErrorDto> errors = UpdateUserValidation.execute(updateUserDto);

        if (!errors.isEmpty()) {
            throw new BlankOrNullFieldsException(errors);
        }

        String emailFromRepository = findUser.getEmail();
        String emailFromDto = updateUserDto.email();

        if (!emailFromRepository.equals(emailFromDto)) {
            if (userRepository.existsByEmail(updateUser.getEmail())) {
                throw new DuplicateEmailException();
            }
        }
        if (isUserTypeExists(updateUserDto)) throw new UserTypeException() ;

        return userRepository.save(updateUser);
    }

    private static boolean isUserTypeExists(UpdateUserDto userType){
        return userType.userType() == UserType.COMMON || userType.userType() == UserType.MERCHANT;
    }
}
