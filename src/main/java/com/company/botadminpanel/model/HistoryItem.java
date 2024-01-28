package com.company.botadminpanel.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String userId;
    Integer questionId;
    Integer answerId;
    String question;
    String answer;
    String correctAnswer;
    Boolean isTrue;
    @ManyToOne
    QuestionHistory questionHistory;

}