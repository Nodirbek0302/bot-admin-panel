package com.company.botadminpanel.dto;

import jakarta.persistence.Column;
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
public class UpdateStoryDTO {
    @NotBlank
    String body;
    @NotBlank
    String score;
    @NotNull
    Integer sectionId;
    @NotNull
    Integer userId;
}
