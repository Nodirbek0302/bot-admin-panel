package com.company.botadminpanel.service.quiz;

import com.company.botadminpanel.dto.AddQuizDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.Question;

import java.util.List;

public interface QuestionService {

    ApiResult<List<Question>> list();

    ApiResult<Question> byId(Integer id);

    ApiResult<Boolean> add(AddQuizDTO addQuizDTO);

    ApiResult<Boolean> update(Integer id, AddQuizDTO addQuizDTO);

    ApiResult<Boolean> delete(Integer id);
}
