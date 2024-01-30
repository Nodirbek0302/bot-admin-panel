package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.AddBookDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.BookDTO;
import com.company.botadminpanel.model.Book;
import com.company.botadminpanel.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<ApiResult<List<BookDTO>>> list(){
        return ResponseEntity.ok(bookService.list());
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResult<BookDTO>> getBook(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.getById(id));
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<ApiResult<BookDTO>> add(@Valid @RequestBody AddBookDTO addBookDTO){
        return ResponseEntity.ok(bookService.add(addBookDTO));
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResult<BookDTO>> update(@Valid @RequestBody AddBookDTO addBookDTO, @PathVariable Integer id){
        return ResponseEntity.ok(bookService.update(id,addBookDTO));
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.delete(id));
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PatchMapping("/change-activity/{id}")
    public HttpEntity<ApiResult<BookDTO>> deleteIsActive(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.deleteIsActive(id));
    }
}
