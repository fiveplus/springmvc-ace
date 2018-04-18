package com.fiveplus.dao;

import com.fiveplus.entity.Group;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GroupMapper extends Mapper<Group> {
	Group queryByName(@Param("name") String name);
}
