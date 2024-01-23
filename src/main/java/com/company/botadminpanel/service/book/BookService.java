package com.company.botadminpanel.service.book;

import com.company.botadminpanel.dto.AddBookDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.Book;

import java.util.List;

public interface BookService {
    ApiResult<List<Book>> list();

    ApiResult<Book> getById(Integer id);

    ApiResult<Boolean> add(AddBookDTO addBookDTO);

    ApiResult<Boolean> update(Integer id, AddBookDTO addBookDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<Boolean> deleteIsActive(Integer id);
}
