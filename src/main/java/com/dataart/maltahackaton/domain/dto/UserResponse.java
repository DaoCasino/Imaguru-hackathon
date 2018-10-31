package com.dataart.maltahackaton.domain.dto;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String walletAddress;

}
