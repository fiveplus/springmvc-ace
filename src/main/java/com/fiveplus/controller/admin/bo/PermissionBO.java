package com.fiveplus.controller.admin.bo;

import com.fiveplus.entity.Permission;

import java.io.Serializable;
import java.util.List;

public class PermissionBO implements Serializable{
	private Permission permission;
	private List<Permission> pers;
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public List<Permission> getPers() {
		return pers;
	}
	public void setPers(List<Permission> pers) {
		this.pers = pers;
	}
}
