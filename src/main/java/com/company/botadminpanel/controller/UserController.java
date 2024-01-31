package com.company.botadminpanel.controller;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.UserDTO;
import com.company.botadminpanel.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping
    public HttpEntity<ApiResult<List<UserDTO>>> list(){
        return ResponseEntity.ok(userService.list());
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResult<UserDTO>> getBook(@PathVariable String id){
        return ResponseEntity.ok(userService.getById(id));
    }
}
