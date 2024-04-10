package com.rafaelpavan.controller.user;

import com.rafaelpavan.models.dtos.user.UpdateUserDto;
import com.rafaelpavan.models.dtos.user.UserDto;
import com.rafaelpavan.models.entities.user.User;
import com.rafaelpavan.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDto userDto){
        var newUser = this.userService.saveUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UpdateUserDto updateUserDto){
        var updateUSer = this.userService.updateUser(id, updateUserDto);
        return new ResponseEntity<>(updateUSer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable(value = "id") UUID id){
        var findUser = this.userService.findUserById(id);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }
}
