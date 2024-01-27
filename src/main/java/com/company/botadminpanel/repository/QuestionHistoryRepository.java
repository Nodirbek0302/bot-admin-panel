package com.company.botadminpanel.repository;

import com.company.botadminpanel.model.QuestionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionHistoryRepository extends JpaRepository<QuestionHistory,Integer> {
}
