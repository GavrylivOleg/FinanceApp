package com.finance.app.service.implementation;


import com.finance.app.domain.User;
import com.finance.app.domain.enums.Role;
import com.finance.app.persistance.UserRepository;
import com.finance.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public void saveUser(final User user) {
        LOGGER.info("UserServiceImpl.saveUser()");

        final String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        user.setRoles(roles);

        userRepository.save(user);
        LOGGER.info("UserServiceImpl.saveUser() finished");
    }

}
