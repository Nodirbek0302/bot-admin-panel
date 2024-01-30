package com.company.botadminpanel.service;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.QuestionHistory;
import com.company.botadminpanel.model.RewardQuiz;
import com.company.botadminpanel.repository.QuestionHistoryRepository;
import com.company.botadminpanel.repository.RewardQuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class RewardQuizService {

    private final RewardQuizRepository rewardQuizRepository;
    private final QuestionHistoryRepository questionHistoryRepository;

    public ApiResult<Boolean> reward(Integer id) {

        QuestionHistory questionHistory = questionHistoryRepository.findById(id).orElseThrow();

        RewardQuiz rewardQuiz = new RewardQuiz(questionHistory);
        rewardQuizRepository.save(rewardQuiz);

        return ApiResult.successResponse(true);
    }

}
