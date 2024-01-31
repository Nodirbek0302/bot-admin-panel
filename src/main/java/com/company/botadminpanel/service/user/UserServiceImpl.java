package com.company.botadminpanel.service.user;

import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.UserDTO;
import com.company.botadminpanel.exceptions.RestException;
import com.company.botadminpanel.mapper.UserMapper;
import com.company.botadminpanel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ApiResult<List<UserDTO>> list() {
        return ApiResult.successResponse(userMapper.mapToBookDTOList(userRepository.findAll()));
    }

    @Override
    public ApiResult<UserDTO> getById(String id) {
        return ApiResult.successResponse(userMapper.mapToUserDTO(userRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday user mavjud emas", HttpStatus.BAD_REQUEST))));
    }
}
