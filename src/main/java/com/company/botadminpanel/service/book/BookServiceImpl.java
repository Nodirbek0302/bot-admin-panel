package com.company.botadminpanel.service.book;

import com.company.botadminpanel.dto.AddBookDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.model.Book;
import com.company.botadminpanel.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public ApiResult<List<Book>> list() {
        return ApiResult.successResponse(bookRepository.findAll());
    }

    @Override
    public ApiResult<Book> getById(Integer id) {
        return ApiResult.successResponse(bookRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday kitob mavjud emas", HttpStatus.BAD_REQUEST)));
    }

    @Override
    public ApiResult<Boolean> add(AddBookDTO addBookDTO) {

        Optional<Book> book = bookRepository.findByAuthorAndTitle(addBookDTO.getAuthor(), addBookDTO.getTitle());
        if (book.isPresent())
            throw RestException.restThrow("Bunday kitob oldin qo'shilgan",HttpStatus.BAD_REQUEST);

        Book book1 = Book.builder().isActive(true).author(addBookDTO.getAuthor()).title(addBookDTO.getTitle()).build();
        bookRepository.save(book1);

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> update(Integer id, AddBookDTO addBookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday kitob mavjud emas", HttpStatus.BAD_REQUEST));

        book.setAuthor(addBookDTO.getAuthor());
        book.setTitle(addBookDTO.getTitle());
        bookRepository.save(book);

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        bookRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> deleteIsActive(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> RestException.restThrow("Bunday book mavjud emas", HttpStatus.BAD_REQUEST));
        book.setIsActive(false);
        bookRepository.save(book);
        return ApiResult.successResponse(true);
    }
}
