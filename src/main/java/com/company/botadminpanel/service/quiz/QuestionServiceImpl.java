package com.company.botadminpanel.service.quiz;

import com.company.botadminpanel.dto.AddQuizDTO;
import com.company.botadminpanel.dto.AnswerDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.UpdateAnswerDTO;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.model.Answer;
import com.company.botadminpanel.model.Book;
import com.company.botadminpanel.model.Question;
import com.company.botadminpanel.repository.AnswerRepository;
import com.company.botadminpanel.repository.QuestionRepository;
import com.company.botadminpanel.service.answer.AnswerService;
import com.company.botadminpanel.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    public final BookService bookService;
    private final AnswerService answerService;

    @Override
    public ApiResult<List<Question>> list() {
        return ApiResult.successResponse(questionRepository.findAll());
    }

    @Override
    public ApiResult<Question> byId(Integer id) {
        return ApiResult.successResponse(questionRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday savol mavjud emas", HttpStatus.BAD_REQUEST)));
    }

    @Override
    public ApiResult<Boolean> add(AddQuizDTO addQuizDTO) {
        Book book = bookService.getById(addQuizDTO.getBookId()).getData();

        Question question = Question.builder().name(addQuizDTO.getName())
                .book(book)
                .price(addQuizDTO.getPrice())
                .build();
        questionRepository.save(question);

        List<AnswerDTO> dtos = addQuizDTO.getAnswerDTOList();

        List<Answer> answers = new ArrayList<>();

        for (AnswerDTO answerDTO : dtos) {
            Answer answer = Answer.builder()
                    .question(question)
                    .correct(answerDTO.getCorrect())
                    .name(answerDTO.getName())
                    .build();
            answers.add(answer);
        }

        answerRepository.saveAll(answers);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> update(Integer id, AddQuizDTO addQuizDTO) {

        Book book = bookService.getById(addQuizDTO.getBookId()).getData();
        Question question = byId(id).getData();

        question.setBook(book);
        question.setName(addQuizDTO.getName());
        question.setPrice(addQuizDTO.getPrice());
        questionRepository.save(question);

        List<Answer> answers = answerService.byQuizId(id).getData();
        for (Answer answer : answers) {
            for (AnswerDTO answerDTO : addQuizDTO.getAnswerDTOList()) {
                answer.setCorrect(answerDTO.getCorrect());
                answer.setName(answer.getName());
                break;
            }
        }
        answerRepository.saveAll(answers);

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        questionRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }
}
