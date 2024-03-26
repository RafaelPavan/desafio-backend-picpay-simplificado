package com.rafaelpavan.services.user;

import com.rafaelpavan.exceptions.user.BlankOrNullFieldsException;
import com.rafaelpavan.exceptions.user.DuplicateDcoException;
import com.rafaelpavan.exceptions.user.DuplicateEmailException;
import com.rafaelpavan.exceptions.user.UserNotFoundException;
import com.rafaelpavan.models.dtos.user.UserDto;
import com.rafaelpavan.models.dtos.validations.ErrorDto;
import com.rafaelpavan.models.entities.user.User;
import com.rafaelpavan.repositories.user.UserRepository;
import com.rafaelpavan.validations.FieldsValidations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserDto data)  {
        var newUser = new User(data);

        List<ErrorDto> errors = FieldsValidations.execute(data);

        if(!errors.isEmpty()) {
            System.out.println(errors + "errorss");
            throw new BlankOrNullFieldsException(errors);
        }

//        var isDocumentAlreadyExists = userRepository.findAll()
//                .stream()
//                .anyMatch(user -> user.getDocument().equals(newUser.getDocument()));

        if (this.userRepository.existsByDocument(newUser.getDocument())) {
            throw new DuplicateDcoException();
        }
        if (this.userRepository.existsByEmail(newUser.getEmail())) {
            throw new DuplicateEmailException();
        }

//        CpfValidation.validate(newUser.getDocument());

//        EmailValidation.emailValidation(newUser.getEmail());


        this.userRepository.save(newUser);

        return newUser;
    }


    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserById(UUID id) {
        return this.userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
    }
}
