package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.AddSectionDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.SectionDTO;
import com.company.botadminpanel.model.Section;
import com.company.botadminpanel.service.section.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<ApiResult<List<SectionDTO>>> sectionList() {
        return ResponseEntity.ok(sectionService.list());
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResult<SectionDTO>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(sectionService.getById(id));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<ApiResult<SectionDTO>> add(@Valid @RequestBody AddSectionDTO addSectionDTO) {
        return ResponseEntity.ok(sectionService.add(addSectionDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResult<SectionDTO>> update(@PathVariable Integer id, @Valid @RequestBody AddSectionDTO addSectionDTO) {
        return ResponseEntity.ok(sectionService.update(id, addSectionDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(sectionService.delete(id));
    }

    @GetMapping("/by-book-id/{id}")
    public HttpEntity<ApiResult<List<SectionDTO>>> byBookId(@PathVariable Integer id) {
        return ResponseEntity.ok(sectionService.byBookId(id));
    }

}
