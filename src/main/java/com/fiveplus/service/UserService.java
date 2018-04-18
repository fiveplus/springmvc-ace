package com.fiveplus.service;

import com.fiveplus.controller.admin.bo.UserBO;
import com.fiveplus.dao.UserMapper;
import com.fiveplus.entity.User;
import com.fiveplus.utils.PasswordHelper;
import com.fiveplus.utils.PropertiesUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserService extends BaseService<User>{
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	public UserBO getUserById(Integer id){
		return userMapper.getUserById(id);
	}

	public User getUserByLoginName(String loginName){
		return userMapper.getUserByLoginName(loginName);
	}
	
	public PageInfo<UserBO> getUserList(int page){
		PageHelper.startPage(page, 10);
		List<UserBO> list = userMapper.getUserList();
		PageInfo<UserBO> p = new PageInfo<UserBO>(list);
		return p;
	}
	
	public List<UserBO> getUserList(){
		return userMapper.getUserList();
	}
	
	public Integer saveUser(User us){

		us.setPortrait("");
		
		PropertiesUtils util = new PropertiesUtils();
		us.setPassword(util.getProperty("init.pass"));
		us = passwordHelper.encryptPassword(us);
		Integer count = saveSelect(us);
		return count;
	}

}
