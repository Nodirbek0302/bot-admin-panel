package com.company.botadminpanel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SectionDTO {
    @NotNull
    Integer id;
    @NotBlank
    String name;
    @NotNull
    BookDTO book;
}
