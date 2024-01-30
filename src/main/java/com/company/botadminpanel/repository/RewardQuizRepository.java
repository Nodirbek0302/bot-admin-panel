package com.company.botadminpanel.repository;

import com.company.botadminpanel.model.RewardQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardQuizRepository extends JpaRepository<RewardQuiz, Integer> {



}
