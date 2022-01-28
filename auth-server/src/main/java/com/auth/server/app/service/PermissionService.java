package com.auth.server.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.server.app.mapper.PermissionMapper;
import com.auth.server.domain.entity.TbPermission;
import com.auth.server.domain.service.TbPerService;
import com.auth.server.web.dto.response.PermissionResponse;

@Service
public class PermissionService {

	@Autowired
	private TbPerService tbPerService;

	private PermissionMapper mapper = Mappers.getMapper(PermissionMapper.class);

	public PermissionResponse findByPermissionId(String permissionId) {
		return tbPerService.findByPermissionId(permissionId)
				.map(root -> findSubByPermission(mapper.entityToDto(root)))
				.orElse(null);
	}

	/**
	 * 查詢該權限底下的子權限，使用廣度優先搜尋方式。
	 * 
	 * 
	 */
	public PermissionResponse findSubByPermission(PermissionResponse start) {
		Queue<PermissionResponse> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();

		queue.offer(start);
		visited.add(start.getId());
		while (!queue.isEmpty()) {
			int sz = queue.size();
			for (int i = 0; i < sz; i++) {
				PermissionResponse cur = queue.poll();
				Optional<List<TbPermission>> subPermissionsOpt = tbPerService.findSubPerById(cur.getId());
				subPermissionsOpt.ifPresent((List<TbPermission> subPerEntityList) -> {
					cur.setSubPermissions(new ArrayList<>());
					List<PermissionResponse> subPermissions = cur.getSubPermissions();
					for (TbPermission subPerEntity : subPerEntityList) {
						PermissionResponse subPer = mapper.entityToDto(subPerEntity);
						if (!visited.contains(subPer.getId())) {
							queue.offer(subPer);
							visited.add(subPer.getId());
							subPermissions.add(subPer);
						}
					}
				});
			}
		}
		return start;
	}

	/**
	 * 查詢該權限底下的子權限，使用廣度優先搜尋方式。
	 * 
	 * 
	 */
	public PermissionResponse findSubByPermission(PermissionResponse start, List<TbPermission> perEntityList) {
		Queue<PermissionResponse> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();

		queue.offer(start);
		visited.add(start.getId());
		while (!queue.isEmpty()) {
			int sz = queue.size();
			for (int i = 0; i < sz; i++) {
				PermissionResponse cur = queue.poll();
				List<PermissionResponse> subPermissions = perEntityList.stream()
						// 尋找該節點的子節點權限
						.filter(perEntity -> StringUtils.equals(perEntity.getParentId(), cur.getId()))
						// 避免重覆
						.filter(perEntity -> !visited.contains(perEntity.getId())).map(perEntity -> {
							PermissionResponse subPer = mapper.entityToDto(perEntity);
							queue.offer(subPer);
							visited.add(subPer.getId());
							return subPer;
						}).collect(Collectors.toList());
				cur.setSubPermissions(subPermissions);
			}
		}
		return start;
	}

	public List<PermissionResponse> findPerTreeByUserId(String userId) {
		Optional<List<TbPermission>> perEntityListOps = tbPerService.findByUserId(userId);
		if (perEntityListOps.isEmpty()) {
			return Collections.emptyList();
		}
		List<TbPermission> perEntityList = perEntityListOps.get();
		Set<String> perEntityMap = perEntityList.stream().map(TbPermission::getId).collect(Collectors.toSet());

		return perEntityList.stream()
				// 找出root節點。
				.filter(perEntity -> !perEntityMap.contains(perEntity.getParentId()))
				.map(item -> findSubByPermission(mapper.entityToDto(item), perEntityList)).collect(Collectors.toList());
	}

	public Optional<List<PermissionResponse>> findPerListByUserId(String userId) {
		return tbPerService.findByUserId(userId).map(entityList -> mapper.entityListToDtoList(entityList));
	}
}
