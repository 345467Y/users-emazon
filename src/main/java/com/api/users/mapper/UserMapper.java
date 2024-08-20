package com.api.users.mapper;

import com.api.users.domain.model.UserDTO;
import com.api.users.persistencia.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userDTOToUserEntity(UserDTO user);

    UserDTO userEntityToUserDTO(UserEntity user);

}
