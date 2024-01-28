package com.company.botadminpanel.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToMany
    List<HistoryItem> answers;
    String userId;
    String bookId;
    Integer numberOfQuestion;
    Integer correctAnswer;

}