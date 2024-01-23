package com.company.botadminpanel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddQuizDTO {
    @NotBlank
    String name;
    @NotNull
    Integer bookId;
    @NotNull
    Double price;
    @NotNull
    List<AnswerDTO> answerDTOList;
}
