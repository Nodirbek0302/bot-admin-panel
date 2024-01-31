package com.company.botadminpanel.service.user;


import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.dto.UserDTO;

import java.util.List;

public interface UserService {
    ApiResult<List<UserDTO>> list();

    ApiResult<UserDTO> getById(String id);
}
