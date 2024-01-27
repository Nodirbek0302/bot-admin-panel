package com.company.botadminpanel.service.quizhistory;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.QuestionHistory;

import java.util.List;

public interface QuestionHistoryService {


    ApiResult<List<QuestionHistory>> list();
}
