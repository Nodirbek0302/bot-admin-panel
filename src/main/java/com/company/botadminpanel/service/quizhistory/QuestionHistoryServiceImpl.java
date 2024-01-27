package com.company.botadminpanel.service.quizhistory;

import com.company.botadminpanel.dto.ApiResult;
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
        return ApiResult.successResponse(questionHistoryRepository.findAll());
    }
}
