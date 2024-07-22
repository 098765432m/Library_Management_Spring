package com.project.Library_Management_Spring_BackEnd.controller;

import com.project.Library_Management_Spring_BackEnd.dto.UserCreationDto;
import com.project.Library_Management_Spring_BackEnd.dto.UserDto;
import com.project.Library_Management_Spring_BackEnd.dto.UserUpdatingDto;
import com.project.Library_Management_Spring_BackEnd.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDto> getUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id){
        return userService.getOne(id);
    }

    @PostMapping("/")
    public String addUser(@RequestBody @Valid UserCreationDto userCreationDto){
        userService.addUser(userCreationDto);
        return "Tạo tài khoản thành công!";
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody UserUpdatingDto userUpdatingDto){
        userService.updateUser(id, userUpdatingDto);
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable String id){
        userService.deleteUser(id);
        return "Xóa tài khoản với id là " + id + " thành công!";
    }
}
