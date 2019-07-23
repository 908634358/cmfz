package com.baizhi;

import com.baizhi.repository.jpa.AdminJpaRepository;
import com.baizhi.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {
    @Autowired
    private AdminJpaRepository adminJpaRepository;
    @Test
    public void contextLoads() {

        Admin adminByUsernameAndPassword = adminJpaRepository.findAdminByUsernameAndPassword("qq4", "qq");
        System.out.println("adminByUsernameAndPassword = " + adminByUsernameAndPassword);
/*
       Admin admin = new Admin();
       admin.setId(UUID.randomUUID().toString());
       admin.setUsername("qq4");
       admin.setNickname("qq");
       admin.setPassword("qq");
       adminJpaRepository.save(admin);
       System.out.println("admin = " + admin);*/
       /*  List<Admin> all = adminJpaRepository.findAll();
        all.forEach(admin -> {
            System.out.println("admin = " + admin);
        });*/
    }

}
