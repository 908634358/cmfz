package com.baizhi;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.pojo.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerTests {
    @Autowired
    private UserDao userDao;

    @Test
    public void contextLoads() throws Exception {
        List<City> list = userDao.selectAllCountBySex("男");
        list.forEach(s -> {
            System.out.println("s = " + s);
        });
    }

}
