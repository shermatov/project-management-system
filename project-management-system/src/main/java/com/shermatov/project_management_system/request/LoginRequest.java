package com.shermatov.project_management_system.request;


import lombok.*;

@Data
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {
    }

}
