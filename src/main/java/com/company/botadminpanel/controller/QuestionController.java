package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.AddQuizDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.QuestionDTO;
import com.company.botadminpanel.service.quiz.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<ApiResult<List<QuestionDTO>>> list(){
        return ResponseEntity.ok(questionService.list());
    }

    @GetMapping("/by-book-id/{id}")
    public HttpEntity<ApiResult<List<QuestionDTO>>> list2(@PathVariable Integer id){
        return ResponseEntity.ok(questionService.listByBookId(id));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResult<QuestionDTO>> byId(@PathVariable Integer id){
        return ResponseEntity.ok(questionService.byId(id));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<ApiResult<QuestionDTO>> add(@Valid @RequestBody AddQuizDTO addQuizDTO){
      return ResponseEntity.ok(questionService.add(addQuizDTO));
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResult<QuestionDTO>> update(@PathVariable Integer id, @Valid @RequestBody AddQuizDTO addQuizDTO){
        return ResponseEntity.ok(questionService.update(id,addQuizDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(questionService.delete(id));
    }

}
