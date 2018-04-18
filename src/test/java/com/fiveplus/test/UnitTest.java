package com.fiveplus.test;

import com.fiveplus.entity.User;
import com.fiveplus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring*.xml"})
public class UnitTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        User user = userService.queryById(1);
        System.out.println(user);
    }
}
