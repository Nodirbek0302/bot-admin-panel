package com.company.botadminpanel.service.story;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.SectionDTO;
import com.company.botadminpanel.dto.StoryDTO;
import com.company.botadminpanel.dto.UpdateStoryDTO;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.mapper.BookMapper;
import com.company.botadminpanel.mapper.UserMapper;
import com.company.botadminpanel.model.Section;
import com.company.botadminpanel.model.Story;
import com.company.botadminpanel.model.User;
import com.company.botadminpanel.repository.StoryRepository;
import com.company.botadminpanel.repository.UserRepository;
import com.company.botadminpanel.service.section.SectionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    public StoryServiceImpl(StoryRepository storyRepository, UserMapper userMapper, BookMapper bookMapper, @Lazy SectionService sectionService, UserRepository userRepository) {
        this.storyRepository = storyRepository;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
        this.sectionService = sectionService;
        this.userRepository = userRepository;
    }

    private final SectionService sectionService;
    private final UserRepository userRepository;

    @Override
    public ApiResult<List<StoryDTO>> list(boolean read, String sort, Sort.Direction sortType) {
        if (read) return ApiResult.successResponse(mapToStoryDTO(storyRepository.findAll(sort, sortType.name())));
        return ApiResult.successResponse(mapToStoryDTO(storyRepository.findAllIsNull(sort, sortType.name())));
    }

    @Override
    public ApiResult<StoryDTO> byId(Integer id) {
        return ApiResult.successResponse(mapToStoryDTO(storyRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday Story mavjud emas", HttpStatus.BAD_REQUEST))));
    }


    @Override
    public ApiResult<StoryDTO> update(Integer id, UpdateStoryDTO storyDTO) {
        Story story = storyRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday story mavjud emas id = " + id, HttpStatus.BAD_REQUEST));

        SectionDTO section = sectionService.getById(storyDTO.getSectionId()).getData();

        User user = userRepository.findById(storyDTO.getUserId())
                .orElseThrow(() -> RestException.restThrow("Bunday User mavjud emas id => " + storyDTO.getUserId(), HttpStatus.BAD_REQUEST));

        story.setBody(storyDTO.getBody());
        story.setUser(user);
        story.setScore(storyDTO.getScore());
        story.setSection(Section.builder()
                .id(section.getId())
                .name(section.getName())
                .book(bookMapper.ToMapBook(section.getBook()))
                .build());

        storyRepository.save(story);

        return ApiResult.successResponse(mapToStoryDTO(story));
    }


    @Override
    public void delete(Integer id) {
        storyRepository.deleteById(id);
    }

    @Override
    public ApiResult<Boolean> score(Integer score, Integer id) {
        Story story = storyRepository.findById(id).orElseThrow();
        story.setScore(score.toString());
        storyRepository.save(story);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> unscore(Integer id) {
        Story story = storyRepository.findById(id).orElseThrow();
        story.setScore(null);
        storyRepository.save(story);
        return ApiResult.successResponse(true);
    }

    private List<StoryDTO> mapToStoryDTO(List<Story> all) {
        List<StoryDTO> list = new ArrayList<>();
        for (Story story : all) {
            StoryDTO storyDTO = StoryDTO.builder()
                    .id(story.getId())
                    .body(story.getBody())
                    .score(story.getScore())
                    .userDTO(userMapper.mapToUserDTO(story.getUser()))
                    .sectionDTO(mapToSectionDTO(story.getSection()))
                    .build();
            list.add(storyDTO);
        }
        return list;
    }

    private StoryDTO mapToStoryDTO(Story story) {
        return StoryDTO.builder()
                .id(story.getId())
                .body(story.getBody())
                .score(story.getScore())
                .userDTO(userMapper.mapToUserDTO(story.getUser()))
                .sectionDTO(mapToSectionDTO(story.getSection()))
                .build();
    }

    public SectionDTO mapToSectionDTO(Section section) {
        return SectionDTO.builder()
                .id(section.getId())
                .name(section.getName())
                .book(bookMapper.mapToBookDTO(section.getBook()))
                .build();
    }
}
