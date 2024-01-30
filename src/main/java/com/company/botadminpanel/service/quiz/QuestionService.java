package com.company.botadminpanel.service.quiz;

import com.company.botadminpanel.dto.AddQuizDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.QuestionDTO;
import com.company.botadminpanel.model.Question;

import java.util.List;

public interface QuestionService {

    ApiResult<List<QuestionDTO>> list();

    ApiResult<QuestionDTO> byId(Integer id);

    ApiResult<QuestionDTO> add(AddQuizDTO addQuizDTO);

    ApiResult<QuestionDTO> update(Integer id, AddQuizDTO addQuizDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<List<QuestionDTO>> listByBookId(Integer id);
}
