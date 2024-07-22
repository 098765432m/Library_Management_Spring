package com.project.Library_Management_Spring_BackEnd.controller;

import com.project.Library_Management_Spring_BackEnd.dto.request.ApiResponse;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserCreationDto;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserDto;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserUpdatingDto;
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
    public ApiResponse<List<UserDto>> getUsers(){
        ApiResponse<List<UserDto>> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.getAll());

        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDto> getUser(@PathVariable String id){
        ApiResponse<UserDto> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.getOne(id));

        return apiResponse;
    }

    @PostMapping("/")
    public ApiResponse<String> addUser(@RequestBody @Valid UserCreationDto userCreationDto){

        ApiResponse<String> apiResponse = new ApiResponse<>();
        userService.addUser(userCreationDto);

        apiResponse.setMessage("Tạo tài khoản thành công!");
        return apiResponse;
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody UserUpdatingDto userUpdatingDto){
        userService.updateUser(id, userUpdatingDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> removeUser(@PathVariable String id){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        userService.deleteUser(id);

        apiResponse.setMessage("Xóa tài khoản với id là " + id + " thành công!");
        return apiResponse;
    }
}
