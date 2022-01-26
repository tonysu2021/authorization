package com.auth.server.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.server.app.dto.response.PermissionResponse;
import com.auth.server.domain.entity.TbPermission;
import com.auth.server.domain.service.TbPerService;

@Service
public class PerService {

	private static final Logger logger = LoggerFactory.getLogger(PerService.class);

	@Autowired
	private TbPerService tbPerService;

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
		logger.info("root : {}", root.getId());
		PermissionResponse start = entityToDTO(root);
		queue.offer(start);
		visited.add(start.getId());
		while (!queue.isEmpty()) {
			int sz = queue.size();
			for (int i = 0; i < sz; i++) {
				PermissionResponse cur = queue.poll();
				Optional<List<TbPermission>> neighborsOpt = tbPerService.findByParentId(cur.getId());
				neighborsOpt.ifPresent((List<TbPermission> neighbors) -> {
					cur.setSubPermissions(new ArrayList<>());
					List<PermissionResponse> subPermissions = cur.getSubPermissions();
					for (TbPermission subPerEntity : neighbors) {
						logger.info("subPer : {}", subPerEntity.getId());
						PermissionResponse subPer = entityToDTO(subPerEntity);
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

	private PermissionResponse entityToDTO(TbPermission entity) {
		PermissionResponse permission = new PermissionResponse();
		permission.setParentId(entity.getParentId());
		permission.setId(entity.getId());
		permission.setName(entity.getName());
		permission.setEnname(entity.getEnname());
		permission.setUrl(entity.getUrl());
		return permission;
	}
}
