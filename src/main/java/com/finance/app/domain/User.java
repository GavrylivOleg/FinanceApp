package com.finance.app.domain;


import com.finance.app.domain.enums.UserProjectPermit;
import com.finance.app.domain.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Size(min = 2, max = 20)
    private String firstName;

    @Size(min = 2, max = 20)
    private String lastName;

    @Email
    @NotNull
    private String email;

    @Size(min = 2, max = 20)
    @NotNull
    private String password;

    @Min(18)
    private int age;

    private List<UserProjectPermit> roles;

    private String status = UserStatus.ACTIVE.name();
}
