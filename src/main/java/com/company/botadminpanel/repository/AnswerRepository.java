package com.company.botadminpanel.repository;

import com.company.botadminpanel.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    List<Answer>  findByQuestionId(Integer questionId);

}
