package com.atguigu.mpdemo;

import com.atguigu.mpdemo.entity.User;
import com.atguigu.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MpdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    //查询user表中的数据
    @Test
    void finaAll() {
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }

    //添加操作
    @Test
    public void addUser(){
        User user = new User();
        user.setName("lucy");
        user.setAge(30);
        user.setEmail("lucy@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    //修改操作
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2L);
        user.setAge(120);
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

}
