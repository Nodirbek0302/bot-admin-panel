package com.company.botadminpanel.service.quizhistory;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.HistoryItem;
import com.company.botadminpanel.model.QuestionHistory;
import com.company.botadminpanel.repository.QuestionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionHistoryServiceImpl implements QuestionHistoryService {
    private final QuestionHistoryRepository questionHistoryRepository;

    @Override
    public ApiResult<List<QuestionHistory>> list() {
        List<QuestionHistory> all = questionHistoryRepository.findAll();
        for (QuestionHistory history : all) {
            history.setCorrectAnswer(countCorrectAnswer(history.getAnswers()));
            history.setNumberOfQuestion(history.getAnswers().size());
        }
        return ApiResult.successResponse(all);
    }

    private Integer countCorrectAnswer(List<HistoryItem> answers) {
        int correctAns = 0;
        for (HistoryItem answer : answers) {
            if (answer.getIsTrue())
                correctAns+=1;
        }
        return correctAns;
    }


}
