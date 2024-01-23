package com.company.botadminpanel.service.auth;


import com.company.botadminpanel.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> login(LoginDTO loginDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken,String refreshToken);
}
