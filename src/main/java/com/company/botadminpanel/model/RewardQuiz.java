package com.company.botadminpanel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

public class RewardQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public RewardQuiz(QuestionHistory questionHistory) {
        this.questionHistory = questionHistory;
    }

    @ManyToOne
    private QuestionHistory questionHistory;
}
