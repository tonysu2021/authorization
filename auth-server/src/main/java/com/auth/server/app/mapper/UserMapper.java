package com.auth.server.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.auth.server.domain.entity.TbUser;
import com.auth.server.web.dto.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "userName", target = "userName")
	public UserResponse entityToDto(TbUser entity);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "userName", target = "userName")	
	public List<UserResponse> entityListToDtoList(List<TbUser> entityList);
}
