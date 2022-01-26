package com.auth.server.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.server.app.dto.response.PermissionResponse;
import com.auth.server.app.mapper.PermissionMapper;
import com.auth.server.domain.entity.TbPermission;
import com.auth.server.domain.service.TbPerService;

@Service
public class PerService {

	@Autowired
	private TbPerService tbPerService;
	
	private PermissionMapper mapper = Mappers.getMapper(PermissionMapper.class);

	/**
	 * 使用廣度優先搜尋方式。
	 * 
	 * */
	public PermissionResponse findById(String id) {
		Queue<PermissionResponse> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();

		TbPermission root = tbPerService.findById(id).orElse(null);
		if (root == null) {
			return null;
		}
		PermissionResponse start = mapper.entityToDto(root);
		queue.offer(start);
		visited.add(start.getId());
		while (!queue.isEmpty()) {
			int sz = queue.size();
			for (int i = 0; i < sz; i++) {
				PermissionResponse cur = queue.poll();
				Optional<List<TbPermission>> subPermissionsOpt = tbPerService.findByParentId(cur.getId());
				subPermissionsOpt.ifPresent((List<TbPermission> neighbors) -> {
					cur.setSubPermissions(new ArrayList<>());
					List<PermissionResponse> subPermissions = cur.getSubPermissions();
					for (TbPermission subPerEntity : neighbors) {
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
}
