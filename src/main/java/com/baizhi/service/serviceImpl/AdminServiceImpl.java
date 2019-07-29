package com.baizhi.service.serviceImpl;

import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public void findLogin(String code, String username, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String securityCode = (String) session.getAttribute("securityCode");
        if (securityCode.equals(code)) {
            try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);

            } catch (UnknownAccountException u) {
                throw new RuntimeException("用戶名错误");
            } catch (IncorrectCredentialsException e) {
                throw new RuntimeException("密码错误");
            }

        } else {
            throw new RuntimeException("验证码错误");
        }

    }
}
