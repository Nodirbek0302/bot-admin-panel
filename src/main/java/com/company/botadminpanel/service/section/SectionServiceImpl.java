package com.company.botadminpanel.service.section;

import com.company.botadminpanel.dto.AddSectionDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.SectionDTO;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.model.Book;
import com.company.botadminpanel.model.Section;
import com.company.botadminpanel.repository.SectionRepository;
import com.company.botadminpanel.service.book.BookService;
import com.company.botadminpanel.service.story.StoryService;
import com.company.botadminpanel.service.story.StoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final BookService bookService;
    private final StoryServiceImpl storyService;

    @Override
    public ApiResult<List<SectionDTO>> list() {
        List<Section> all = sectionRepository.findAll();
        return ApiResult.successResponse(all.stream().map(storyService::mapToSectionDTO).toList());
    }

    @Override
    public ApiResult<SectionDTO> getById(Integer id) {
        return ApiResult.successResponse(storyService.mapToSectionDTO(sectionRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday Section mavjud emas", HttpStatus.BAD_REQUEST))));
    }

    @Override
    public ApiResult<Boolean> add(AddSectionDTO addSectionDTO) {
        Optional<Section> byNameAndBookId = sectionRepository.findByNameAndBook_Id(addSectionDTO.getName(), addSectionDTO.getBookId());

        if (byNameAndBookId.isPresent())
            throw RestException.restThrow("Bunday Section mavjud",HttpStatus.BAD_REQUEST);

        Section section = Section.builder()
                .name(addSectionDTO.getName())
                .book(bookService.getById(addSectionDTO.getBookId()).getData())
                .build();
        sectionRepository.save(section);

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> update(Integer id, AddSectionDTO addSectionDTO) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday section mavjud emas", HttpStatus.BAD_REQUEST));

        section.setName(addSectionDTO.getName());
        section.setBook(bookService.getById(addSectionDTO.getBookId()).getData());
        sectionRepository.save(section);

        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<List<SectionDTO>> byBookId(Integer id) {
        List<Section> byBookId = sectionRepository.findByBook_Id(id);

        return ApiResult.successResponse(byBookId.stream().map(storyService::mapToSectionDTO).toList());
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        sectionRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }

}
