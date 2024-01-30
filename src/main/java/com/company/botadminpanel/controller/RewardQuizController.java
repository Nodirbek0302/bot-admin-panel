package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.service.RewardQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reward")
@RequiredArgsConstructor
public class RewardQuizController {
    private final RewardQuizService rewardQuizService;

    @PostMapping("/{id}")
    public HttpEntity<ApiResult<Boolean>> reward(@PathVariable Integer id){
        return ResponseEntity.status(201).body(rewardQuizService.reward(id));
    }

}
