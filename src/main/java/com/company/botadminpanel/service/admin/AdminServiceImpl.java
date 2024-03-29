package com.company.botadminpanel.service.admin;

import com.company.botadminpanel.dto.AddAdminDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.enums.RoleEnum;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.model.Admin;
import com.company.botadminpanel.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean add(AddAdminDTO addAdminDTO) {

        Optional<Admin> admin = adminRepository.findByUsername(addAdminDTO.getUserName());
        if (admin.isPresent())
            throw RestException.restThrow("Bunday username bilan admin mavjud", HttpStatus.BAD_REQUEST);

        adminRepository.save(Admin.builder()
                .firstName(addAdminDTO.getFirstName())
                .lastName(addAdminDTO.getLastName())
                .password(passwordEncoder.encode(addAdminDTO.getPassword()))
                .phoneNumber(addAdminDTO.getPhoneNumber())
                .username(addAdminDTO.getUserName())
                .roleEnum(RoleEnum.ADMIN).build());
        return true;
    }

    @Override
    public ApiResult<List<Admin>> list() {
        return ApiResult.successResponse(adminRepository.findAll());
    }
}
