package com.company.botadminpanel.mapper;

import com.company.botadminpanel.dto.BookDTO;
import com.company.botadminpanel.dto.UserDTO;
import com.company.botadminpanel.model.Book;
import com.company.botadminpanel.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

     BookDTO mapToBookDTO(Book book);

}
