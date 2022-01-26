package com.auth.server.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.server.app.dto.response.PermissionResponse;
import com.auth.server.app.service.PerService;

@RestController
@RequestMapping(value = "/permission")
public class PerController {

	@Autowired
	private PerService perService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PermissionResponse> findById(@PathVariable String id) {
		PermissionResponse permissionDto = perService.findById(id);
		if (permissionDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(permissionDto, HttpStatus.OK);
	}
}
