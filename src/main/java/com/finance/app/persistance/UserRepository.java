package com.finance.app.persistance;


import com.finance.app.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends MongoRepository<User,String>{

      Optional<User> findByEmail(String email);
}
