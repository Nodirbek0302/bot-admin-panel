package com.company.botadminpanel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoryDTO {
    @NotNull
    Integer id;
    @NotBlank
    String body;
    @NotBlank
    String score;
    @NotNull
    SectionDTO sectionDTO;
    @NotNull
    UserDTO userDTO;
}
