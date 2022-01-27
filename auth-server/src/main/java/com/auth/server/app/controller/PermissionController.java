package com.auth.server.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.server.app.dto.request.PermissionPostRequest;
import com.auth.server.app.dto.response.PermissionResponse;
import com.auth.server.app.service.PermissionService;

@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@GetMapping(value = "/{permissionId}")
	public ResponseEntity<PermissionResponse> findByPermissionId(@PathVariable String permissionId) {
		PermissionResponse permissionDto = permissionService.findByPermissionId(permissionId);
		if (permissionDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(permissionDto, HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/tree")
	public ResponseEntity<List<PermissionResponse>> findPerTreeByUserId(@PathVariable String userId) {
		List<PermissionResponse> permissionDtoList = permissionService.findPerTreeByUserId(userId);
		if (CollectionUtils.isEmpty(permissionDtoList)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(permissionDtoList, HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/list")
	public ResponseEntity<List<PermissionResponse>> findPerListByUserId(@PathVariable String userId) {
		Optional<List<PermissionResponse>> permissionDtoListOpt = permissionService.findPerListByUserId(userId);
		return permissionDtoListOpt
				.map(permissionDtoList -> new ResponseEntity<>(permissionDtoList, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	@PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PermissionResponse> createPermission(@RequestBody PermissionPostRequest request){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
}
