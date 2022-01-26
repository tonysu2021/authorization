package com.auth.server.domain.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.server.app.dto.request.UserPatchRequest;
import com.auth.server.app.dto.request.UserPostRequest;
import com.auth.server.domain.entity.TbUser;
import com.auth.server.domain.repository.TbUserRepository;

@Service("tbUserService")
public class TbUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TbUserRepository repository;

	public List<TbUser> findAll() {
		return repository.findAll();
	}

	public Optional<TbUser> findByUserName(String userName) {
		return repository.findByUserName(userName);
	}

	public Optional<TbUser> findById(String userId) {
		return repository.findById(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	public TbUser registerUser(UserPostRequest request) {
		TbUser tbUser = new TbUser();
		tbUser.setId("user-" + UUID.randomUUID().toString());
		tbUser.setUserName(request.getUserName());
		tbUser.setPassword(passwordEncoder.encode(request.getPassword()));
		tbUser.setPhone(request.getPhone());
		tbUser.setEmail(request.getEmail());
		tbUser.setCreateTime(Instant.now());
		tbUser.setModifyTime(Instant.now());
		return repository.save(tbUser);
	}

	/**
	 * 切記，若外層有@Transactional，並且外層方法拋出異常，會讓內層一起回滾。 <br>
	 * 備註: @Transactional 預設propagation = Propagation.REQUIRED
	 * 
	 * */
	@Transactional(rollbackFor = Exception.class)
	public TbUser updateUser(TbUser tbUser, UserPatchRequest request) {
		tbUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
		tbUser.setModifyTime(Instant.now());
		return repository.save(tbUser);
	}

	public boolean matches(String password, String hashPassword) {
		return passwordEncoder.matches(password, hashPassword);
	}
}
