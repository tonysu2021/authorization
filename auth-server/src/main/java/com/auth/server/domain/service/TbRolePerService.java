package com.auth.server.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.server.domain.entity.TbRolePermission;
import com.auth.server.domain.repository.TbRolePerRepository;

@Service
public class TbRolePerService {

	@Autowired
	private TbRolePerRepository repository;
	
	@Transactional(rollbackFor = Exception.class)
	public List<TbRolePermission> saveAll(List<TbRolePermission> entities) {
		return repository.saveAll(entities);
	}
}
