package com.company.botadminpanel.mapper;

import com.company.botadminpanel.dto.UserDTO;
import com.company.botadminpanel.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

     UserDTO mapToUserDTO(User user);

}
