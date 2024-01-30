package com.company.botadminpanel.dto;

import com.company.botadminpanel.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionHistoryDTO {

    private Integer id;
    private UserDTO userDTO;
    private BookDTO bookDTO;
    private Double totalPrice;
    private Integer numberOfQuestion;
    private Integer correctAnswer;

}
