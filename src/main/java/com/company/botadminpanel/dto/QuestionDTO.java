package com.company.botadminpanel.dto;

import com.company.botadminpanel.model.Answer;
import com.company.botadminpanel.model.Book;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionDTO {
    Integer id;
    BookDTO book;
    Double price;
    String name;
    List<AnswerDTO> answerList;
}
