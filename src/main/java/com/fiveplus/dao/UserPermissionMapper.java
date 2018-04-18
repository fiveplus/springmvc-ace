package com.fiveplus.dao;

import com.fiveplus.entity.UserPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserPermissionMapper extends Mapper<UserPermission> {
	int deletePermissionByUserId(@Param("userId") Integer userId);
}
