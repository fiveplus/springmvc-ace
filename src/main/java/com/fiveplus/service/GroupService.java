package com.fiveplus.service;

import com.fiveplus.dao.GroupMapper;
import com.fiveplus.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("groupService")
public class GroupService extends BaseService<Group>{
	@Autowired
	private GroupMapper groupMapper;
	
	public Group queryByName(String name){
		return groupMapper.queryByName(name);
	}
	
}
