package com.baizhi.controller.poi;

import com.baizhi.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poi")
public class PoiExport {
    @Autowired
    private UserDao userDao;

    public void test1() {
       /* // 存数据库中查出需要导出的数据
        List<User> users = userDao.selectAll();
        // 使用Poi进行导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户信息表");
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);*/
    }
}
