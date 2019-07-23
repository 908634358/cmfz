package com.baizhi.controller;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import com.baizhi.util.service.CrudMapper;
import com.baizhi.util.service.SelectAllPagingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    @Autowired
    private GuruDao guruDao;

    @Qualifier("selectAllPagingMapperImpl")
    @Autowired
    private SelectAllPagingMapper<Guru> selectAllPaging;
    @Qualifier("crudMapperImpl")
    @Autowired
    private CrudMapper<Guru> crudMapper;
    @RequestMapping("/selectAllAlbum")
    @ResponseBody
    public Map<String, Object> getPage(Integer page, Integer rows) {
        System.out.println("page = " + page);
        System.out.println("rows = " + rows);
        Map<String, Object> stringObjectMap = selectAllPaging.selectAllPaging(page, rows, guruDao, new Guru());
        return stringObjectMap;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, Object> editBanner(String oper, Guru guru) {

        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            guru.setId(UUID.randomUUID().toString());
            map = crudMapper.add(guru, guruDao, guru.getId());
        }
        if (oper.equals("edit")) {
            map = guruService.edit(guru, guruDao);
        }
        if (oper.equals("del")) {
            map = crudMapper.del(guru, guruDao, guru.getId());
        }
        return map;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String id, MultipartFile photo, HttpServletRequest request) throws IOException {
        System.out.println("photo = " + photo);
        System.out.println("id:" + id);
//        文件上传
        photo.transferTo(new File(request.getSession().getServletContext().getRealPath("/article/img"), photo.getOriginalFilename()));
        System.out.println("cover upload over!");
//        根据id修改图片的名字
        Guru guru = new Guru();
        guru.setId(id);
        guru.setPhoto(photo.getOriginalFilename());
        System.out.println("banner = " + guru);
        guruService.edit(guru, guruDao);
    }

}
