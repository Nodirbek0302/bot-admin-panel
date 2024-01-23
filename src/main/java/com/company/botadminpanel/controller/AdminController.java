package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.AddAdminDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.service.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public HttpEntity<ApiResult<Boolean>> addAdmin(@Valid @RequestBody AddAdminDTO addAdminDTO){
      return ResponseEntity.ok(ApiResult.successResponse(adminService.add(addAdminDTO)));
    }
}
