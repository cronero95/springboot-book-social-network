package com.springbootprojects.booknetworkapi.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotBlank(message = "first name can't be empty")
    private String firstName;

    @NotBlank(message = "last name can't be empty")
    private String lastName;

    @NotBlank(message = "email can't be empty")
    @Email(message = "this email structure is not valid")
    private String email;

    @Size(min = 10, message = "password should have 10 characters a least")
    private String password;
}
