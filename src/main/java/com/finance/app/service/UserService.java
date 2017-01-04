package com.finance.app.service;


import com.finance.app.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService  {

    void saveUser(User user);

}
