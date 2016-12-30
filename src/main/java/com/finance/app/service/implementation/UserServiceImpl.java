package com.finance.app.service.implementation;


import com.finance.app.domain.User;
import com.finance.app.domain.enums.UserProjectPermit;
import com.finance.app.infrastructute.exception.ContentNotFoundException;
import com.finance.app.persistance.UserRepository;
import com.finance.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends FinanceBaseServiceImpl<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void save(User user) {
        LOGGER.info("UserServiceImpl.save()");

        final User newUser = new User();

        newUser.setFirstName(user.getFirstName());

        newUser.setLastName(user.getLastName());

        newUser.setEmail(user.getEmail());

        newUser.setAge(user.getAge());

        final String encodedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encodedPassword);

        List<UserProjectPermit> roles = new ArrayList<>();
        roles.add(UserProjectPermit.USER);
        newUser.setRoles(roles);

        userRepository.save(newUser);
        LOGGER.info("UserServiceImpl.save() finished");
    }

}
