package com.project.Library_Management_Spring_BackEnd.service;

import com.project.Library_Management_Spring_BackEnd.dto.request.UserCreationDto;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserDto;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserUpdatingDto;
import com.project.Library_Management_Spring_BackEnd.entity.User;
import com.project.Library_Management_Spring_BackEnd.exception.AppException;
import com.project.Library_Management_Spring_BackEnd.exception.ErrorCode;
import com.project.Library_Management_Spring_BackEnd.mapper.UserMapper;
import com.project.Library_Management_Spring_BackEnd.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    //Get all users
    public List<UserDto> getAll(){
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> userMapper.UserToUserDto(user)).toList();
    }

    //Get A User
    public UserDto getOne(String id){
            User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            UserDto userDto = userMapper.UserToUserDto(user);

            return userDto;
    }

    //add one user
    @Transactional
    public void addUser(UserCreationDto userCreationDto){

        if(userRepository.existsByUsername(userCreationDto.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User newUser = userMapper.UserCreationDtoToUser(userCreationDto);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));

        userRepository.save(newUser);
    }

    //update User
    @Transactional
    public void updateUser(String id, UserUpdatingDto userUpdatingDto){
            User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            user = userMapper.updateUser(user, userUpdatingDto);
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            user.setPassword(passwordEncoder.encode(userUpdatingDto.getPassword()));

            userRepository.save(user);
    }

    //delete One
    @Transactional
    public void deleteUser(String id){
        if (userRepository.existsById(id)){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        userRepository.deleteById(id);
    }
}
