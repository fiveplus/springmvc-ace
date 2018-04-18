package com.fiveplus.service;

import com.fiveplus.dao.UserPermissionMapper;
import com.fiveplus.entity.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userPermissionService")
public class UserPermissionService extends BaseService<UserPermission>{
	
	@Autowired
	private UserPermissionMapper userPermissionMapper;
	
	public int deletePermissionByUserId(Integer userId){
		return userPermissionMapper.deletePermissionByUserId(userId);
	}
	
}
