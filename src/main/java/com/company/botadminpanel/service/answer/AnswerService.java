package com.company.botadminpanel.service.answer;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.UpdateAnswerDTO;
import com.company.botadminpanel.model.Answer;

import java.util.List;

public interface AnswerService {
    ApiResult<List<Answer>> list();

    ApiResult<List<Answer>> byQuizId(Integer id);

    ApiResult<Answer> update(Integer id, UpdateAnswerDTO updateAnswerDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<Answer> add(UpdateAnswerDTO updateAnswerDTO);
}