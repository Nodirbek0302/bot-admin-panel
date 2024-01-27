package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.QuestionHistory;
import com.company.botadminpanel.service.quizhistory.QuestionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz-history")
public class QuestionHistoryController {

    private final QuestionHistoryService questionHistoryService;

    public HttpEntity<ApiResult<List<QuestionHistory>>> list(){
        return ResponseEntity.ok(questionHistoryService.list());
    }

}
