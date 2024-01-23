package com.company.botadminpanel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(@NotBlank @Email String email, @NotBlank String password, @NotBlank String perPassword,
                          @NotBlank String name){
}
