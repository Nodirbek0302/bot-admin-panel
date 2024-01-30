package com.company.botadminpanel.service.quiz;

import com.company.botadminpanel.dto.*;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.mapper.QuizMapper;
import com.company.botadminpanel.model.Answer;
import com.company.botadminpanel.model.Book;
import com.company.botadminpanel.model.Question;
import com.company.botadminpanel.repository.AnswerRepository;
import com.company.botadminpanel.repository.BookRepository;
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
    private final BookRepository bookRepository;
    private final QuizMapper quizMapper;

    @Override
    public ApiResult<List<QuestionDTO>> list() {
        return ApiResult.successResponse(quizMapper.mapToQuestionDTOList(questionRepository.findAll()));
    }

    @Override
    public ApiResult<QuestionDTO> byId(Integer id) {
        return ApiResult.successResponse(quizMapper.mapToQuestionDTO(questionRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday savol mavjud emas", HttpStatus.BAD_REQUEST))));
    }

    @Override
    public ApiResult<QuestionDTO> add(AddQuizDTO addQuizDTO) {
        Book book = bookRepository.findById(addQuizDTO.getBookId()).orElseThrow();

        Question question = Question.builder().name(addQuizDTO.getName())
                .book(book)
                .price(addQuizDTO.getPrice())
                .build();
        questionRepository.save(question);

        List<AnswerDTO> dtos = addQuizDTO.getAnswerList();

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
        return ApiResult.successResponse(quizMapper.mapToQuestionDTO(question));
    }

    @Override
    public ApiResult<QuestionDTO> update(Integer id, AddQuizDTO addQuizDTO) {
        Book book = bookRepository.findById(addQuizDTO.getBookId()).orElseThrow();

//        Book book = bookService.getById(addQuizDTO.getBookId()).getData();
//        Question question = byId(id).getData();
        // me
        Question question = questionRepository.findById(id).orElseThrow();

        question.setBook(book);
        question.setName(addQuizDTO.getName());
        question.setPrice(addQuizDTO.getPrice());
        questionRepository.save(question);

        List<Answer> answers = answerService.byQuizId(id).getData();
        for (Answer answer : answers) {
            for (AnswerDTO answerDTO : addQuizDTO.getAnswerList()) {
                answer.setCorrect(answerDTO.getCorrect());
                answer.setName(answer.getName());
                break;
            }
        }
        answerRepository.saveAll(answers);

        return ApiResult.successResponse(quizMapper.mapToQuestionDTO(question));
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        questionRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<List<QuestionDTO>> listByBookId(Integer id) {
        List<Question> questions = questionRepository.findAllByBookId(id);
        return ApiResult.successResponse(quizMapper.mapToQuestionDTOList(questions));
    }
}
