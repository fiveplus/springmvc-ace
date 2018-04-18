package com.fiveplus.dao;

import com.fiveplus.controller.admin.bo.UserBO;
import com.fiveplus.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User>{
	User getUserByLoginName(@Param("loginName") String loginName);
	List<UserBO> getUserList();
	UserBO getUserById(@Param("id") Integer id);
}
