package com.finance.app.domain;


import com.finance.app.domain.enums.UserProjectPermit;
import com.finance.app.domain.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private int age;

    private List<UserProjectPermit> roles;

    private String status = UserStatus.ACTIVE.name();
}
