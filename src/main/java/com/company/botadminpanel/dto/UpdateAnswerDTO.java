package com.company.botadminpanel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAnswerDTO {
    @NotNull
    Integer quizId;
    @NotBlank
    String name;
    @NotNull
    Boolean correct;
}
