package com.company.botadminpanel.mapper;

import com.company.botadminpanel.dto.AnswerDTO;
import com.company.botadminpanel.model.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default AnswerDTO mapToAnswerDTO(Answer answer) {
        return new AnswerDTO(answer.getName(), answer.getCorrect());
    }
}
