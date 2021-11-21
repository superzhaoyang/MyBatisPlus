package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /*
    测试乐观锁
     */
    @Test
    public void testOptimisticLocker() {
        //根据id查询
        User user = userMapper.selectById(1462333049088786434L);
        user.setName("张三");
        userMapper.updateById(user);
    }

    /*
    修改
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1462322200571834370L);
        user.setName("lucymarycc");
        int count = userMapper.updateById(user);
        System.out.println(count);
    }

    /*
    增加
     */
    @Test
    public void testAdd(){
        User user = new User();
        user.setName("王五");
        user.setAge(20);
        user.setEmail("1243@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    /*
    多个id批量查询
     */
    @Test
    public void testSelect1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    /*
    简单条件查询
     */
    @Test
    public void testSelect2() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Jack");
        columnMap.put("age", 20);
        List<User> users = userMapper.selectByMap(columnMap);
        System.out.println(users);
    }

    /*
    分页查询
     */
    @Test
    public void testSelectPage() {
        Page<User> page = new Page(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        //返回对象得到所有分页数据
        long pages = userPage.getPages(); //总页数
        long current = userPage.getCurrent(); //当前页
        List<User> records = userPage.getRecords(); //查询数据集合
        long total = userPage.getTotal(); //总记录数
        boolean hasNext = userPage.hasNext(); //是否有下一页
        boolean hasPrevious = userPage.hasPrevious(); //是否有上一页

        System.out.println(pages);
        System.out.println(current);
        System.out.println(records);
        System.out.println(total);
        System.out.println(hasNext);
        System.out.println(hasPrevious);
    }

    @Test
    public void testSelect() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //ge,gt,le,lt
        //queryWrapper.ge("age", 21);

        //eq,ne
        //queryWrapper.eq("name", "Tom");

        //between, notBetween
        //queryWrapper.between("age", 24, 28);

        //like, notLike, likeLeft, likeRight
        //queryWrapper.like("name", "张");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    /*
    查询列表
     */
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    /*
    根据id删除
     */

    @Test
    public void deleteId() {
        int rows = userMapper.deleteById(1462341072268140546L);
        System.out.println(rows);
    }

    /*
    批量删除
     */
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }

    /*
    条件删除
     */
    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }





}
