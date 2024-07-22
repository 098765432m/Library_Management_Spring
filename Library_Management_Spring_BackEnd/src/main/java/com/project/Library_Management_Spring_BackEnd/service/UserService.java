package com.project.Library_Management_Spring_BackEnd.service;

import com.project.Library_Management_Spring_BackEnd.dto.UserCreationDto;
import com.project.Library_Management_Spring_BackEnd.dto.UserDto;
import com.project.Library_Management_Spring_BackEnd.dto.UserUpdatingDto;
import com.project.Library_Management_Spring_BackEnd.entity.User;
import com.project.Library_Management_Spring_BackEnd.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Get all users
    public List<UserDto> getAll(){
        List<User> users = userRepository.findAll();

        return users.stream().map(this::toDto).toList();
    }

    //Get A User
    public UserDto getOne(String id){
        if (userRepository.existsById(id)){
            User user = userRepository.findById(id).get();

            UserDto userDto = this.toDto(user);

            return userDto;
        } else {
            return null;
        }
    }

    //add one user
    @Transactional
    public void addUser(UserCreationDto userCreationDto){

        if(userRepository.existsByUsername(userCreationDto.getUsername())){
            throw new RuntimeException("Username đã tồn tại!");
        }

        User newUser = new User();

        newUser.setUsername(userCreationDto.getUsername());
        newUser.setPassword(userCreationDto.getPassword());
        newUser.setFirstName(userCreationDto.getFirstName());
        newUser.setLastName(userCreationDto.getLastName());
        newUser.setDob(userCreationDto.getDob());

        userRepository.save(newUser);
    }

    //update User
    @Transactional
    public void updateUser(String id, UserUpdatingDto userUpdatingDto){
        if (userRepository.existsById(id)){
            User user = userRepository.findById(id).get();

            user.setPassword(userUpdatingDto.getPassword());
            user.setFirstName(userUpdatingDto.getFirstName());
            user.setLastName(userUpdatingDto.getLastName());
            user.setDob(userUpdatingDto.getDob());

            userRepository.save(user);
        }
    }

    //delete One
    @Transactional
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    //to Dto
    public UserDto toDto(User user){
        UserDto newUserDto = new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getDob());

        return newUserDto;
    }
}
