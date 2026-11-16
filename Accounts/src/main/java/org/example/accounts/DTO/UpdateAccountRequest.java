package org.example.accounts.DTO;

import lombok.Getter;

public class UpdateAccountRequest {

    @Getter
    private String name;
    @Getter
    private String email;

    @Getter
    private String password;

    @Getter
    private String newPassword;

    @Getter
    private String confirmPassword;
}
