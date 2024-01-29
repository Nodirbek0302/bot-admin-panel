package com.company.botadminpanel.service.section;

import com.company.botadminpanel.dto.AddSectionDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.SectionDTO;
import com.company.botadminpanel.model.Section;

import java.util.List;

public interface SectionService {

    ApiResult<List<SectionDTO>> list();

    ApiResult<SectionDTO> getById(Integer id);

    ApiResult<Boolean> add(AddSectionDTO addSectionDTO);

    ApiResult<Boolean> update(Integer id, AddSectionDTO addSectionDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<List<SectionDTO>> byBookId(Integer id);
}
