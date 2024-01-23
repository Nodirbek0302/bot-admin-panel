package com.company.botadminpanel.service.admin;

import com.company.botadminpanel.dto.AddAdminDTO;
import com.company.botadminpanel.dto.ApiResult;
import com.company.botadminpanel.model.Admin;

import java.util.List;

public interface AdminService {

    Boolean add(AddAdminDTO addAdminDTO);

    ApiResult<List<Admin>> list();
}
