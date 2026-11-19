package org.example.accounts.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBuyerRequest {


    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private String companyName;

}
