package org.example.accounts.DTO;

import lombok.Getter;

public class CreateBuyerRequest {

    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String password;
    @Getter
    private String phoneNumber;
    @Getter
    private String address;
    @Getter
    private String companyName;

}
