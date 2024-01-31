package com.company.botadminpanel.mapper;

import com.company.botadminpanel.dto.UserDTO;
import com.company.botadminpanel.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

     UserDTO mapToUserDTO(User user);

     List<UserDTO> mapToBookDTOList(List<User> all);
}
