package com.auth.server.web.dto.response;

import java.util.List;

public class PermissionResponse implements AbstractResponse{
	
	private String parentId;

	private String id;
	
	private String name;
	
	private String enname;
	
	private String url;
	
	private List<PermissionResponse> subPermissions;
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<PermissionResponse> getSubPermissions() {
		return subPermissions;
	}

	public void setSubPermissions(List<PermissionResponse> subPermissions) {
		this.subPermissions = subPermissions;
	}
}
