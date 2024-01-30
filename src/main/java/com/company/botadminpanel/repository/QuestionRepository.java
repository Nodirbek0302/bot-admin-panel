package com.company.botadminpanel.repository;

import com.company.botadminpanel.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByBookId(Integer id);

    @Override
    Optional<Question> findById(Integer integer);
}
