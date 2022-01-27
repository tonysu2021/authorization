package com.auth.server.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.auth.server.app.dto.response.PermissionResponse;
import com.auth.server.domain.entity.TbPermission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
	
	@Mapping(source = "parentId", target = "parentId")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "enname", target = "enname")
	@Mapping(source = "url", target = "url")
	public PermissionResponse entityToDto(TbPermission entity);
	
	@Mapping(source = "parentId", target = "parentId")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "enname", target = "enname")
	@Mapping(source = "url", target = "url")
	public List<PermissionResponse> entityListToDtoList(List<TbPermission> entityList);
}
