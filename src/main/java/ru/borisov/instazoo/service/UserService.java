package ru.borisov.instazoo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.borisov.instazoo.entity.User;
import ru.borisov.instazoo.entity.enums.ERole;
import ru.borisov.instazoo.exceptions.UserExistException;
import ru.borisov.instazoo.payload.request.SignupRequest;
import ru.borisov.instazoo.repository.UserRepository;
import ru.borisov.instazoo.security.JWTTokenProvider;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRole().add(ERole.ROLE_USER);
        try {
            LOG.info("Saving user {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user" + user.getUsername() + "already exist. Please check credentials");
        }
    }
}
