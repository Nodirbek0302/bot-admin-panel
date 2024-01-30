package com.company.botadminpanel.repository;

import com.company.botadminpanel.model.QuestionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionHistoryRepository extends JpaRepository<QuestionHistory,Integer> {

    @Query("from QuestionHistory qs inner join RewardQuiz rq on qs.id = rq.questionHistory.id")
    List<QuestionHistory> findAllRewarded();

    @Query("from QuestionHistory qs left join RewardQuiz rq on qs.id = rq.questionHistory.id where rq.id is null ")
    List<QuestionHistory> findAllUnRewarded();

}
