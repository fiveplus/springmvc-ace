package com.fiveplus.dao;

import com.fiveplus.entity.Permission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission>{
	List<Permission> getParentMenu();
	List<Permission> getChildPermissionByGroupId(@Param("groupId") Integer groupId);
	List<Permission> getChildPermissionByUserId(@Param("userId") Integer userId);
	List<Permission> getPermissionByParentId(@Param("parentId") String parentId);
	int getCountByParentId(@Param("parentId") String parentId);
	List<Permission> getParentPermission();
}
