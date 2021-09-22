package com.auth.server.controller;

import java.util.List;
import java.util.Optional;

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

import com.auth.server.entity.TbUser;
import com.auth.server.request.UserPostRequest;
import com.auth.server.request.UserPatchRequest;
import com.auth.server.service.TbUserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	@Qualifier("tbUserService")
	private TbUserService tbUserService;
	
	@GetMapping
	public ResponseEntity<List<TbUser>> findAll() {
		List<TbUser> tbUsers = tbUserService.findAll();

		if (CollectionUtils.isEmpty(tbUsers)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tbUsers, HttpStatus.OK);
	}

	@GetMapping(value = "/{userName}")
	public ResponseEntity<TbUser> findUserById(@PathVariable String userName) {
		Optional<TbUser> tbUserOpt = tbUserService.findByUserName(userName);

		if (!tbUserOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		TbUser tbUser = tbUserOpt.get();

		return new ResponseEntity<>(tbUser, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TbUser> registerUser(@RequestBody UserPostRequest request) {
		Optional<TbUser> tbUserOpt = tbUserService.findByUserName(request.getUserName());

		if (tbUserOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(tbUserService.registerUser(request), HttpStatus.OK);
	}

	@PatchMapping(value = "/{userName}")
	public ResponseEntity<TbUser> updateUserPass(@PathVariable String userName, @RequestBody UserPatchRequest request) {
		Optional<TbUser> tbUserOpt = tbUserService.findByUserName(userName);

		if (!tbUserOpt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		TbUser tbUser  = tbUserOpt.get();
		if (!tbUserService.matches(request.getOldPassword(), tbUser.getPassword())) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(tbUserService.updateUser(tbUser, request), HttpStatus.OK);
	}
}
