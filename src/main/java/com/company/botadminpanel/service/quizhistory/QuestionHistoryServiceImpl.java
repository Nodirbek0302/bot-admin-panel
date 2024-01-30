package com.company.botadminpanel.service.quizhistory;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.BookDTO;
import com.company.botadminpanel.dto.QuestionHistoryDTO;
import com.company.botadminpanel.dto.UserDTO;
import com.company.botadminpanel.mapper.BookMapper;
import com.company.botadminpanel.mapper.UserMapper;
import com.company.botadminpanel.model.HistoryItem;
import com.company.botadminpanel.model.Question;
import com.company.botadminpanel.model.QuestionHistory;
import com.company.botadminpanel.repository.BookRepository;
import com.company.botadminpanel.repository.QuestionHistoryRepository;
import com.company.botadminpanel.repository.QuestionRepository;
import com.company.botadminpanel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionHistoryServiceImpl implements QuestionHistoryService {

    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final QuestionRepository questionRepository;
    private final QuestionHistoryRepository questionHistoryRepository;
    private final UserRepository userRepository;
    private final BookRepository  bookRepository;

    @Override
    public ApiResult<List<QuestionHistoryDTO>> list(Boolean rewarded) {

        List<QuestionHistory> questionHistories = rewarded ? questionHistoryRepository.findAllRewarded() : questionHistoryRepository.findAllUnRewarded();

        // Question history DTO list build
        List<QuestionHistoryDTO> all = questionHistories.stream().map(questionHistory -> {

            Double totalPrice = 0D;

            // total price
            for (HistoryItem answer : questionHistory.getAnswers()) {
                Question question = questionRepository.findById(answer.getQuestionId()).orElse(null);
                if (question != null) totalPrice += question.getPrice();
            }

            BookDTO bookDTO = bookMapper.mapToBookDTO(bookRepository.findById(Integer.valueOf(questionHistory.getBookId())).orElse(null));

            UserDTO userDTO = userMapper.mapToUserDTO(userRepository.findById(questionHistory.getUserId()).orElse(null));

            return QuestionHistoryDTO.builder()
                    .id(questionHistory.getId())
                    .numberOfQuestion(questionHistory.getNumberOfQuestion())
                    .correctAnswer(questionHistory.getCorrectAnswer())
                    .totalPrice(totalPrice)
                    .bookDTO(bookDTO)
                    .userDTO(userDTO)
                    .build();
        }).toList();

        return ApiResult.successResponse(all);
    }

    private Integer countCorrectAnswer(List<HistoryItem> answers) {
        int correctAns = 0;
        for (HistoryItem answer : answers) {
            if (answer.getIsTrue())
                correctAns+=1;
        }
        return correctAns;
    }


}
