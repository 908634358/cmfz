package com.baizhi.controller;

import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody

    public Map<String, Object> login(String username, String password, String enCode, HttpServletRequest request) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("enCode = " + enCode);
        Map<String, Object> map = new HashMap<>();
        try {
            adminService.findLogin(enCode, username, password, request);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping("exit")
    public String exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginAdmin");
        return "redirect:/login/login.jsp";
    }
}
