package com.auth.server.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.server.domain.entity.TbPermission;
import com.auth.server.domain.repository.TbPerRepository;

@Service
public class TbPerService {

	@Autowired
	private TbPerRepository repository;

	public Optional<List<TbPermission>> findByUserId(String userId) {
		return repository.findByUserId(userId);
	}

}