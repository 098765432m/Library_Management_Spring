package com.project.Library_Management_Spring_BackEnd.service;

import com.project.Library_Management_Spring_BackEnd.dto.request.UserCreationDto;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserDto;
import com.project.Library_Management_Spring_BackEnd.dto.request.UserUpdatingDto;
import com.project.Library_Management_Spring_BackEnd.entity.User;
import com.project.Library_Management_Spring_BackEnd.exception.AppException;
import com.project.Library_Management_Spring_BackEnd.exception.ErrorCode;
import com.project.Library_Management_Spring_BackEnd.mapper.UserMapper;
import com.project.Library_Management_Spring_BackEnd.repository.RoleRepository;
import com.project.Library_Management_Spring_BackEnd.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository , RoleRepository roleRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    //Get all users
    public List<UserDto> getAll(){

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<User> users = userRepository.findAll();

        return users.stream().map(user -> userMapper.UserToUserDto(user)).toList();
    }

    //Get A User
    public UserDto getOne(String id){
            User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.UserToUserDto(user);
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

        var roles = roleRepository.findAllById(userCreationDto.getRoles());
        log.info(userCreationDto.getRoles().toString());
        newUser.setRoles(new HashSet<>(roles));

        userRepository.save(newUser);
    }

    public UserDto getMyInfo(){
        var context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.UserToUserDto(user);
    }

    //update User
    @Transactional
    public UserDto updateUser(String id, UserUpdatingDto userUpdatingDto){
            User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            user = userMapper.updateUser(user, userUpdatingDto);
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            user.setPassword(passwordEncoder.encode(userUpdatingDto.getPassword()));

            var roles = roleRepository.findAllById(userUpdatingDto.getRoles());
            user.setRoles(new HashSet<>(roles));

            userRepository.save(user);

            return userMapper.UserToUserDto(user);
    }

    //delete One
    @Transactional
    public void deleteUser(String id){
        if (!userRepository.existsById(id)){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        userRepository.deleteById(id);
    }
}
