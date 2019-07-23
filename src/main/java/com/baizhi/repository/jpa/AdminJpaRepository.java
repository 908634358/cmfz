package com.baizhi.repository.jpa;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminJpaRepository extends JpaRepository<Admin, String> {
    Admin findAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Admin findAdminByNicknameAndPassword(String nickName, String password);
}
