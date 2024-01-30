package com.company.botadminpanel.service.book;

import com.company.botadminpanel.dto.AddBookDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.BookDTO;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.mapper.BookMapper;
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
    private final BookMapper bookMapper;

    @Override
    public ApiResult<List<BookDTO>> list() {
        return ApiResult.successResponse(bookMapper.mapToBookDTOList(bookRepository.findAllOrderById()));
    }

    @Override
    public ApiResult<BookDTO> getById(Integer id) {
        return ApiResult.successResponse(bookMapper.mapToBookDTO(bookRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday kitob mavjud emas", HttpStatus.BAD_REQUEST))));
    }

    @Override
    public ApiResult<BookDTO> add(AddBookDTO addBookDTO) {

        Optional<Book> book = bookRepository.findByTitle(addBookDTO.getTitle()+"("+addBookDTO.getAuthor()+")");
        if (book.isPresent())
            throw RestException.restThrow("Bunday kitob oldin qo'shilgan",HttpStatus.BAD_REQUEST);

        Book book1 = Book.builder().isActive(true).title(addBookDTO.getTitle()+"("+addBookDTO.getAuthor()+")").build();
        bookRepository.save(book1);

        return ApiResult.successResponse(bookMapper.mapToBookDTO(book1));
    }

    @Override
    public ApiResult<BookDTO> update(Integer id, AddBookDTO addBookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday kitob mavjud emas", HttpStatus.BAD_REQUEST));
        book.setTitle(addBookDTO.getTitle()+"("+addBookDTO.getAuthor()+")");

        return ApiResult.successResponse(bookMapper.mapToBookDTO(bookRepository.save(book)));
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        bookRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<BookDTO> deleteIsActive(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> RestException.restThrow("Bunday book mavjud emas", HttpStatus.BAD_REQUEST));
        book.setIsActive(!book.getIsActive());
        bookRepository.save(book);
        return ApiResult.successResponse(bookMapper.mapToBookDTO(book));
    }
}
