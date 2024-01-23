package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.UpdateAnswerDTO;
import com.company.botadminpanel.model.Answer;
import com.company.botadminpanel.service.answer.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping
    public HttpEntity<ApiResult<List<Answer>>> list(){
        return ResponseEntity.ok(answerService.list());
    }

    @GetMapping("/{quizId}")
    public HttpEntity<ApiResult<List<Answer>>> getById(@PathVariable Integer quizId){
        return ResponseEntity.ok(answerService.byQuizId(quizId));
    }

    @PostMapping("/add")
    public HttpEntity<ApiResult<Answer>> add(@Valid @RequestBody UpdateAnswerDTO updateAnswerDTO){
        return ResponseEntity.ok(answerService.add(updateAnswerDTO));
    }


    @PutMapping("/update")
    public HttpEntity<ApiResult<Answer>> update(@RequestParam Integer id, @Valid @RequestBody UpdateAnswerDTO updateAnswerDTO){
        return ResponseEntity.ok(answerService.update(id,updateAnswerDTO));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(answerService.delete(id));
    }


}
