package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.AddBookDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.Book;
import com.company.botadminpanel.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public HttpEntity<ApiResult<List<Book>>> list(){
        return ResponseEntity.ok(bookService.list());
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResult<Book>> getBook(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping("/add")
    public HttpEntity<ApiResult<Boolean>> add(@Valid @RequestBody AddBookDTO addBookDTO){
        return ResponseEntity.ok(bookService.add(addBookDTO));
    }

    @PutMapping("/update")
    public HttpEntity<ApiResult<Boolean>> update(@RequestParam Integer id, @Valid @RequestBody AddBookDTO addBookDTO){
        return ResponseEntity.ok(bookService.update(id,addBookDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.delete(id));
    }

    @PatchMapping("/isActive/{id}")
    public HttpEntity<ApiResult<Boolean>> deleteIsActive(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.deleteIsActive(id));
    }
}
