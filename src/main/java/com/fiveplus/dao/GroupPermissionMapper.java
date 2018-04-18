package com.fiveplus.dao;

import com.fiveplus.entity.GroupPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GroupPermissionMapper extends Mapper<GroupPermission> {
	int deletePermissionByGroupId(@Param("groupId") Integer groupId);
}
