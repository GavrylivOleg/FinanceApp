package com.finance.app.infrastructute.security;

import com.finance.app.domain.User;
import com.finance.app.domain.enums.UserProjectPermit;
import com.finance.app.persistance.UserRepository;
import com.finance.app.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("financeUserDetailService")
public class FinanceUserDetailService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinanceUserDetails.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        User user = userOptional.orElseThrow(() -> {
            LOGGER.error("User with username={} not found, skip authorization", email);
            throw new UsernameNotFoundException("Bad Credentials");
        });

        return new FinanceUserDetails(user.getEmail(), user.getPassword(), getGrantedAuthorities(user), user.getStatus(), user.getId());
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> list = new ArrayList<>();

        for (UserProjectPermit role : user.getRoles()) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        }

        return list;
    }

    public static class FinanceUserDetails extends org.springframework.security.core.userdetails.User {

        @Getter
        @Setter
        private String userStatus;

        @Getter
        @Setter
        private String id;

        public FinanceUserDetails(String username, String password,
                                  Collection<? extends GrantedAuthority> authorities, String userStatus, String id) {
            super(username, password, authorities);
            this.userStatus = userStatus;
            this.id = id;
        }

    }
}
