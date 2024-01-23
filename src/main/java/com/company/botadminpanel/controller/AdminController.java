package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.AddAdminDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.Admin;
import com.company.botadminpanel.service.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize(value = "hasAnyAuthority('SUPER_ADMIN')")
    @PostMapping
    public HttpEntity<ApiResult<Boolean>> addAdmin(@Valid @RequestBody AddAdminDTO addAdminDTO){
      return ResponseEntity.ok(ApiResult.successResponse(adminService.add(addAdminDTO)));
    }

    @GetMapping("/list")
    public HttpEntity<ApiResult<List<Admin>>> list(){
        return ResponseEntity.ok(adminService.list());
    }
}
