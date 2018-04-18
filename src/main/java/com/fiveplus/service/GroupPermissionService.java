package com.fiveplus.service;

import com.fiveplus.dao.GroupPermissionMapper;
import com.fiveplus.entity.GroupPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("groupPermissionService")
public class GroupPermissionService extends BaseService<GroupPermission>{
	@Autowired
	private GroupPermissionMapper groupPermissionMapper;
	
	public int deletePermissionByGroupId(Integer groupId){
		return groupPermissionMapper.deletePermissionByGroupId(groupId);
	}
	
	
}
