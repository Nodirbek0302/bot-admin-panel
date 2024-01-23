package com.company.botadminpanel.controller;


import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.LoginDTO;
import com.company.botadminpanel.dto.TokenDTO;
import com.company.botadminpanel.service.auth.AuthService;
import com.company.botadminpanel.utils.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.AUTHE_CONTROLLER_BASE_PATH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(AppConstants.AUTHE_CONTROLLER_LOGIN_PATH)
    public HttpEntity<ApiResult<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO){
      return ResponseEntity.ok(authService.login(loginDTO));
    }

   @PatchMapping(AppConstants.AUTHE_CONTROLLER_REFRESH_TOKEN_PATH)
    public HttpEntity<ApiResult<TokenDTO>> refreshToken(String accessToken,String refreshToken){
        return ResponseEntity.ok(authService.refreshToken(accessToken,refreshToken));
   }
}
