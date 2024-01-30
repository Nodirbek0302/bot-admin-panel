package com.company.botadminpanel.mapper;

import com.company.botadminpanel.dto.QuestionDTO;
import com.company.botadminpanel.model.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    QuestionDTO mapToQuestionDTO(Question book);
    List<QuestionDTO> mapToQuestionDTOList(List<Question> books);
}
