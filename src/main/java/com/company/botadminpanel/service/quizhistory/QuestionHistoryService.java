package com.company.botadminpanel.service.quizhistory;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.QuestionHistoryDTO;
import com.company.botadminpanel.model.QuestionHistory;

import java.util.List;

public interface QuestionHistoryService {


    ApiResult<List<QuestionHistoryDTO>> list(Boolean rewarded);
}
