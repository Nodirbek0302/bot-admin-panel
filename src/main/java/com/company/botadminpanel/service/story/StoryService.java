package com.company.botadminpanel.service.story;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.StoryDTO;
import com.company.botadminpanel.dto.UpdateStoryDTO;
import org.springframework.data.domain.Sort;

import java.util.*;

public interface StoryService {

    ApiResult<List<StoryDTO>> list(boolean read, String sort, Sort.Direction sortType);

    ApiResult<StoryDTO> byId(Integer id);

    ApiResult<StoryDTO> update(Integer id, UpdateStoryDTO storyDTO);

    void delete(Integer id);
}
