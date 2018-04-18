package com.fiveplus.service;

import com.fiveplus.dao.PermissionMapper;
import com.fiveplus.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionService extends BaseService<Permission>{
	@Autowired
	private PermissionMapper permissionMapper;
	
	public List<Permission> getParentMenu(){
		return permissionMapper.getParentMenu();
	}
	
	public List<Permission> getChildPermissionByGroupId(Integer groupId){
		return permissionMapper.getChildPermissionByGroupId(groupId);
	}
	
	public List<Permission> getChildPermissionByUserId(Integer userId){
		return permissionMapper.getChildPermissionByUserId(userId);
	}
	
	public List<Permission> getPermissionByParentId(String parentId){
		return permissionMapper.getPermissionByParentId(parentId);
	}
	
	public int getCountByParentId(String parentId){
		return permissionMapper.getCountByParentId(parentId);
	}
	
	public List<Permission> getParentPermission(){
		return permissionMapper.getParentPermission();
	}
	
}
