package org.example.accounts.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccountRequest {


    private String firstName;

    private String lastName;

    private String comapnyName;

    private String email;

    private String password;

    private String newPassword;

    private String confirmPassword;
}
