package com.company.botadminpanel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddAdminDTO {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String phoneNumber;
    @NotBlank
    String userName;
    @NotBlank
    String password;
}
