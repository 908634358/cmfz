package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.aliyuncs.exceptions.ClientException;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.pojo.City;
import com.baizhi.service.UserService;
import com.baizhi.util.SendMessageUtil;
import com.baizhi.util.service.CrudMapper;
import com.baizhi.util.service.SelectAllPagingMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Qualifier("selectAllPagingMapperImpl")
    @Autowired
    private SelectAllPagingMapper<User> selectAllPaging;
    @Qualifier("crudMapperImpl")
    @Autowired
    private CrudMapper<User> crudMapper;
    @Autowired
    private SendMessageUtil sendMessageUtil;

    @RequestMapping("/selectAll")
    public Map<String, Object> getPage(Integer page, Integer rows) {
        System.out.println("page = " + page);
        System.out.println("rows = " + rows);
        Map<String, Object> map = selectAllPaging.selectAllPaging(page, rows, userDao, new User());
        System.out.println("map = " + map);
        return map;
    }

    /**
     * 注册接口
     *
     * @param session 是一个作用域，整个浏览器有效,我需要从session中获取一个code,作为判断条件
     * @param phone   手机号，用户在注册时必须填写手机号才可免密注册 必填
     * @param code    验证码，当用户输入的code与我作用域中的code一致才可注册 必填
     * @return
     */
    @RequestMapping("/register")
    public User register(HttpSession session, String phone, String code) {
        User user = new User();
        if (code.equals(session.getAttribute("code"))) {
            user.setId(UUID.randomUUID().toString());
            user.setPhoto(phone);
            user.setCreateDate(new Date());
            crudMapper.add(user, userDao, user.getId());
            session.setAttribute("User", user);
            System.out.println("user = " + user);
        }
        return user;

    }

    /**
     * 获取验证码接口
     *
     * @param session 是一个作用域，整个浏览器有效,将得到的code放入session中
     * @param phone   手机号，用户在获取验证码时必须填写手机号才可免密注册 必填
     * @return
     */
    @RequestMapping("/getCode")
    public String getCode(String phone, HttpSession session) {
        String send = null;
        try {
            send = sendMessageUtil.send(phone);
            session.setAttribute("code", send);
            System.out.println("code = " + send);
        } catch (ClientException e) {
            System.out.println("验证码发送失败！！");
            e.printStackTrace();
        }
        session.setAttribute("code", send);
        return send;
    }

    @RequestMapping("/edit")
    public Map<String, Object> editBanner(String oper, User user) {
        System.out.println("oper = " + oper);
        System.out.println("user = " + user);
        Map<String, Object> map = new HashMap<>();
        user.setPhoto(user.getPhoto());
        if (oper.equals("add")) {
            user.setId(UUID.randomUUID().toString());
            user.setCreateDate(new Date());
            map = crudMapper.add(user, userDao, user.getId());
        }
        if (oper.equals("edit")) {
            map = userService.edit(user, userDao);
            System.out.println("Update user = " + user);
        }
        if (oper.equals("del")) {
            map = crudMapper.del(user, userDao, user.getId());
        }
        return map;
    }

    @RequestMapping("/upload")
    public void upload(String id, MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println("============================upload========================");
        System.out.println("photo = " + photo);
        System.out.println("id:" + id);
//        文件上传
        photo.transferTo(new File(request.getSession().getServletContext().getRealPath("/user/img"), photo.getOriginalFilename()));
//        根据id修改图片的名字
        User user = new User();
        user.setId(id);
        user.setCreateDate(new Date());
        user.setPhoto(photo.getOriginalFilename());
        System.out.println("user = " + user);
        userService.edit(user, userDao);
    }

    /**
     *
     */
    @RequestMapping("/out")
    public void out(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 文件的导入
        // 查询所有用户 返回list集合
        List<User> user2 = new ArrayList<>();
        List<User> users = userDao.selectAll();
        users.forEach(user -> {
            String contextPath = request.getContextPath();// 获取项目名
            String photo = user.getPhoto();
            //D:/Desktop/elasticsearch/workspaces/cmfz/src/main/webapp/user/img/
            user.setPhoto("http://localhost:9090/cmfz/user/img/" + photo);
            System.out.println("photo = " + photo);
            System.out.println("user = " + user);
            user2.add(user);
        });
        //导出
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表一号2", "用户表2"),
                User.class, user2);
        String fileName = "用户报表(" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ").xls";

        fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=" + fileName);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/line")
    public Map<String, Object> line() {
        Map<String, Object> map = new HashMap<>();
        List<Integer> count = new ArrayList<>();
        Integer countMonth = null;
        for (int i = 6; i >= 1; i--) {
            Integer firstMoth = userDao.selectAllFirstHalfYear(30 * i);
            Integer nextMonth = userDao.selectAllFirstHalfYear(30 * (i + 1));

            countMonth = nextMonth - firstMoth;
            count.add(countMonth);

        }
        map.put("firstMoth", count);

        return map;
    }

    @RequestMapping(value = "/map")
    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        List<City> man = userDao.selectAllCountBySex("男");
        List<City> woman = userDao.selectAllCountBySex("女");
        map.put("man", man);
        map.put("woman", woman);
        map.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });
        return map;
    }


}
