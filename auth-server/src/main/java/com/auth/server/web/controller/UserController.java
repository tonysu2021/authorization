package com.auth.server.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.server.app.mapper.UserMapper;
import com.auth.server.app.service.UserService;
import com.auth.server.domain.entity.TbUser;
import com.auth.server.domain.service.TbUserService;
import com.auth.server.web.dto.request.UserPatchRequest;
import com.auth.server.web.dto.request.UserPostRequest;
import com.auth.server.web.dto.response.UserResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	@Qualifier("tbUserService")
	private TbUserService tbUserService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	private UserMapper mapper = Mappers.getMapper(UserMapper.class);
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> findAll() throws InterruptedException, ExecutionException  {
		var usersFuture = userService.findAll().exceptionally(e -> {
			logger.error(e.getMessage());
			return null;
		});
		List<TbUser> tbUsers = usersFuture.get();

		if (CollectionUtils.isEmpty(tbUsers)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mapper.entityListToDtoList(tbUsers), HttpStatus.OK);
	}

	@GetMapping(value = "/{userName}")
	public ResponseEntity<UserResponse> findByUserName(@PathVariable String userName) {
		Optional<TbUser> tbUserOpt = userService.findByUserName(userName);

		if (!tbUserOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		TbUser tbUser = tbUserOpt.get();
		
		return new ResponseEntity<>(mapper.entityToDto(tbUser), HttpStatus.OK);
	}

	@PostMapping(value="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> registerUser(@RequestBody UserPostRequest request) {
		Optional<TbUser> tbUserOpt = userService.findByUserName(request.getUserName());

		if (tbUserOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		TbUser tbUser = tbUserService.registerUser(request);
		
		return new ResponseEntity<>(mapper.entityToDto(tbUser), HttpStatus.OK);
	}

	@PatchMapping(value = "/{userName}")
	public ResponseEntity<UserResponse> updateUserPass(@PathVariable String userName, @RequestBody UserPatchRequest request) {
		Optional<TbUser> tbUserOpt = userService.findByUserName(userName);

		if (!tbUserOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		TbUser tbUser  = tbUserOpt.get();
		if (!tbUserService.matches(request.getOldPassword(), tbUser.getPassword())) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		tbUser = userService.updateUser(tbUser, request);
		return new ResponseEntity<>(mapper.entityToDto(tbUser), HttpStatus.OK);
	}
}
