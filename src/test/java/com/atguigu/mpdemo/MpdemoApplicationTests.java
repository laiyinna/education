package com.atguigu.mpdemo;

import com.atguigu.mpdemo.entity.User;
import com.atguigu.mpdemo.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        user.setName("Jerry");
        user.setAge(5);
        user.setEmail("Jerry@qq.com");
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

    //测试乐观锁
    @Test
    public void testOptimisticLocker() {
        //根据id查询出数据
        User user = userMapper.selectById(7L);
        //进行修改
        user.setAge(6);
        int i = userMapper.updateById(user);

    }

    //多个id的批量查询
    @Test
    public void testSelects() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jerry");
        map.put("age", 6);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);

    }

    @Test
    public void testSelectPage() {
        //new Page<>(1,5)第一个参数为当前页，第二个参数为每页显示的记录数
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page, null);
        //得到每页数据的list集合
        page.getRecords().forEach(System.out::println);
        //获取当前页
        System.out.println(page.getCurrent());
        //得到当前总共多少页
        System.out.println(page.getPages());
        //每页显示的记录数
        System.out.println(page.getSize());
        //当前表中的总记录数
        System.out.println(page.getTotal());
        //是否有下一页
        System.out.println(page.hasNext());
        //是否有上一页
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testDeleted(){
        int i = userMapper.deleteById(1L);
        System.out.println(i);
    }


}
