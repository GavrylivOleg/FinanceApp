package com.finance.app.infrastructute.security;

import com.finance.app.domain.User;
import com.finance.app.domain.enums.Role;
import com.finance.app.domain.enums.UserStatus;
import com.finance.app.persistance.UserRepository;
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
import java.util.stream.Collectors;

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

        List<SimpleGrantedAuthority> grantedAuthorityList = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());

        return new FinanceUserDetails(user.getEmail(), user.getPassword(), grantedAuthorityList, user.getStatus(), user.getId());
    }

    public static class FinanceUserDetails extends org.springframework.security.core.userdetails.User {

        @Getter
        @Setter
        private UserStatus userStatus;

        @Getter
        @Setter
        private String id;

        public FinanceUserDetails(String username, String password,
                                  Collection<? extends GrantedAuthority> authorities, UserStatus userStatus, String id) {
            super(username, password, authorities);
            this.userStatus = userStatus;
            this.id = id;
        }

    }
}
