package com.auth.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.server.entity.TbRole;
import com.auth.server.repository.TbRoleRepository;

@Service
public class TbRoleService {

	@Autowired
	private TbRoleRepository repository;
	
	public Optional<TbRole> findByUserId(String userId){
		return repository.findByUserId(userId);
	}
}
