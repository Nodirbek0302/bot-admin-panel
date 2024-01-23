package com.company.botadminpanel.service.auth;

import com.company.botadminpanel.config.jwtFilter.JWTProvider;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.LoginDTO;
import com.company.botadminpanel.dto.TokenDTO;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.model.Admin;
import com.company.botadminpanel.repository.AdminRepository;
import com.company.botadminpanel.utils.AppConstants;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    public AuthServiceImpl(AdminRepository adminRepository,
                           @Lazy AuthenticationManager authenticationManager,JWTProvider jwtProvider) {
        this.adminRepository = adminRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ApiResult<TokenDTO> login(LoginDTO loginDTO) {
        Admin user1 = checkCredential(loginDTO.username(), loginDTO.password());
        return ApiResult.successResponse(generateTokenDTO(user1));
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        if (!accessToken.startsWith(AppConstants.BEARER_TYPE))
            throw RestException.restThrow("Bearer emas");

        accessToken = accessToken.substring(AppConstants.BEARER_TYPE.length()).trim();
        refreshToken = refreshToken.substring(AppConstants.BEARER_TYPE.length()).trim();
        if (!jwtProvider.isExpired(accessToken))
            throw RestException.restThrow("Token muddati tugamagan");

        if (!jwtProvider.validRefreshToken(refreshToken))
            throw RestException.restThrow("Refresh token valid emas");

        String userId = jwtProvider.extractUserIdFromRefreshToken(refreshToken);
        Admin user = findUserById(Integer.valueOf(userId))
                .orElseThrow(() -> RestException.restThrow("User not found: " + userId, HttpStatus.NOT_FOUND));

        TokenDTO tokenDTO = generateTokenDTO(user);

        return ApiResult.successResponse(tokenDTO);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Optional<Admin> findUserById(Integer userId) {
        try {
            if (userId == null)
                return Optional.empty();
            return adminRepository.findById(userId);
        } catch (Exception e) {
           throw RestException.restThrow("Bunday user mavjud emas",HttpStatus.BAD_REQUEST);
        }
    }


    private TokenDTO generateTokenDTO(Admin user) {
        String id = user.getId().toString();
        String accessToken = jwtProvider.createAccessToken(id);
        String refreshToken = jwtProvider.createRefreshAccessToken(id);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Admin checkCredential(String username, String password) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return (Admin) authentication.getPrincipal();
    }
}
