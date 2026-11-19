package org.example.accounts.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSellerRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;

    private String companyName;
}
