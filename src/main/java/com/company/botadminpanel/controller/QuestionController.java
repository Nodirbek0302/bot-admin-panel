package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.AddQuizDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.Question;
import com.company.botadminpanel.service.quiz.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/list")
    public HttpEntity<ApiResult<List<Question>>> list(){
        return ResponseEntity.ok(questionService.list());
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResult<Question>> byId(@PathVariable Integer id){
        return ResponseEntity.ok(questionService.byId(id));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<ApiResult<Boolean>> add(@Valid @RequestBody AddQuizDTO addQuizDTO){
      return ResponseEntity.ok(questionService.add(addQuizDTO));
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PutMapping("/update")
    public HttpEntity<ApiResult<Boolean>> update(@RequestParam Integer id, @Valid AddQuizDTO addQuizDTO){
        return ResponseEntity.ok(questionService.update(id,addQuizDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(questionService.delete(id));
    }

}
