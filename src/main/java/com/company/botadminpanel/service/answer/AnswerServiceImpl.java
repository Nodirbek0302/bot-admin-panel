package com.company.botadminpanel.service.answer;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.UpdateAnswerDTO;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.model.Answer;
import com.company.botadminpanel.model.Question;
import com.company.botadminpanel.repository.AnswerRepository;
import com.company.botadminpanel.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public ApiResult<List<Answer>> list() {
      return ApiResult.successResponse(answerRepository.findAll());
    }

    @Override
    public ApiResult<List<Answer>> byQuizId(Integer id) {
        return ApiResult.successResponse(answerRepository.findByQuestionId(id));
    }

    @Override
    public ApiResult<Answer> add(UpdateAnswerDTO updateAnswerDTO) {
        Question question = questionRepository.findById(updateAnswerDTO.getQuizId())
                .orElseThrow(() -> RestException.restThrow("Bunday savol mavjud emas", HttpStatus.BAD_REQUEST));

        Answer answer = Answer.builder()
                .name(updateAnswerDTO.getName())
                .question(question)
                .correct(updateAnswerDTO.getCorrect()).build();

        return ApiResult.successResponse(answerRepository.save(answer));
    }

    @Override
    public ApiResult<Answer> update(Integer id, UpdateAnswerDTO updateAnswerDTO) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday answer mavjud emas", HttpStatus.BAD_REQUEST));
        Question question = questionRepository.findById(updateAnswerDTO.getQuizId())
                .orElseThrow(() -> RestException.restThrow("Bunday savol mavjud emas ", HttpStatus.BAD_REQUEST));


        answer.setName(updateAnswerDTO.getName());
        answer.setQuestion(question);
        answer.setCorrect(updateAnswerDTO.getCorrect());
        answerRepository.save(answer);

        return ApiResult.successResponse(answer);
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        answerRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }
}
