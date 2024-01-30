package com.company.botadminpanel.mapper;

import com.company.botadminpanel.dto.BookDTO;
import com.company.botadminpanel.dto.SectionDTO;
import com.company.botadminpanel.model.Book;
import com.company.botadminpanel.model.Section;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectionMapper {
     SectionDTO mapToBookDTO(Section book);
}
