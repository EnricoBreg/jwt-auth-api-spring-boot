package com.terricabrel.auth_api.dtos;


/*
 * TODO: Possibile estensione da inserire per la validazione della request body in Spring Boot
 */
public class LoginUserDto {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
