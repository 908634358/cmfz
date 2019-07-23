package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class pageController {
    @RequestMapping("/")
    public String main() {
        return "redirect:/login/login.jsp";
    }

    @ResponseBody
    @RequestMapping("/getCode")
    public void getCode(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //      获取验证码数字
        String securityCode = SecurityCode.getSecurityCode();
        //        存到session中
        request.getSession().setAttribute("securityCode", securityCode);
        System.out.println("securityCode:" + securityCode);
        //        获取验证码图片
        BufferedImage image = SecurityImage.createImage(securityCode);
        //        设置响应内容
        response.setContentType("image/png");
        //        将图片以流的形式响应出去
        ImageIO.write(image, "png", response.getOutputStream());
    }
}
