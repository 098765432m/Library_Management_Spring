package com.project.Library_Management_Spring_BackEnd.config;

import com.project.Library_Management_Spring_BackEnd.entity.User;
import com.project.Library_Management_Spring_BackEnd.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;

@Configuration
public class ApplicationInitConfig {

    private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationInitConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();

//                roles.add(Role.ADMIN.name());
//                roles.add(Role.USER.name());

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .firstName("admin")
                        .lastName("admin")
                        .dob(LocalDate.now())
//                        .roles(roles)
                        .build();

                userRepository.save(user);

                log.warn("Admin user has been created with default username-password: admin-admin");
            }
        };
    }
}
