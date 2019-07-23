package com.baizhi.service.serviceImpl;

import com.baizhi.entity.Admin;
import com.baizhi.repository.jpa.AdminJpaRepository;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminJpaRepository adminJpaRepository;

    @Override
    public void findLogin(String code, String username, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String securityCode = (String) session.getAttribute("securityCode");
        if (securityCode.equals(code)) {
            Admin admin = new Admin();
            admin.setUsername(username);
            Admin loginAdmin = adminJpaRepository.findAdminByUsernameAndPassword(username, password);
            if (loginAdmin != null) {
                if (loginAdmin.getPassword().equals(password)) {
                    session.setAttribute("loginAdmin", loginAdmin);
                } else {
                    throw new RuntimeException("密码错误！");
                }
            } else {
                throw new RuntimeException("用户名不存在！");
            }
        } else {
            throw new RuntimeException("验证码错误!");
        }
    }
}
