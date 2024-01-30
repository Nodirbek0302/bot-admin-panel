package com.company.botadminpanel.service.book;

import com.company.botadminpanel.dto.AddBookDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.BookDTO;
import com.company.botadminpanel.model.Book;

import java.util.List;

public interface BookService {
    ApiResult<List<BookDTO>> list();

    ApiResult<BookDTO> getById(Integer id);

    ApiResult<BookDTO> add(AddBookDTO addBookDTO);

    ApiResult<BookDTO> update(Integer id, AddBookDTO addBookDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<BookDTO> deleteIsActive(Integer id);
}
