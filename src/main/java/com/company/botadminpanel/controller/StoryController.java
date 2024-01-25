package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.StoryDTO;
import com.company.botadminpanel.dto.UpdateStoryDTO;
import com.company.botadminpanel.service.story.StoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<ApiResult<List<StoryDTO>>> list(@RequestParam boolean read,
                                                      @RequestParam(defaultValue = "score") String sort,
                                                      @RequestParam(defaultValue = "ASC") Sort.Direction sortType){
        return ResponseEntity.ok(storyService.list(read,sort,sortType));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResult<StoryDTO>> byId(@PathVariable Integer id){
        return ResponseEntity.ok(storyService.byId(id));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PutMapping("/update")
    public HttpEntity<ApiResult<StoryDTO>> update(@RequestParam Integer id, @Valid @RequestBody UpdateStoryDTO storyDTO){
        return ResponseEntity.ok(storyService.update(id,storyDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        storyService.delete(id);
    }

}
