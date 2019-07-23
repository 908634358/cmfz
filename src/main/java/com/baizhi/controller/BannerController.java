package com.baizhi.controller;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.baizhi.util.service.CrudMapper;
import com.baizhi.util.service.SelectAllPagingMapper;
import com.baizhi.util.service.UploadUtil;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerDao bannerDao;

    @Autowired
    private BannerService bannerService;

    @Qualifier("selectAllPagingMapperImpl")
    @Autowired
    private SelectAllPagingMapper<Banner> selectAllPaging;
    @Qualifier("crudMapperImpl")
    @Autowired
    private CrudMapper<Banner> crudMapper;
    @Autowired
    private UploadUtil uploadUtil;

    @RequestMapping("/selectAllBanner")
    @ResponseBody
    public Map<String, Object> getPage(Integer page, Integer rows) {
        System.out.println("page = " + page);
        System.out.println("rows = " + rows);
        Map<String, Object> stringObjectMap = selectAllPaging.selectAllPaging(page, rows, bannerDao, new Banner());
        return stringObjectMap;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, Object> editBanner(String oper, Banner banner) {

        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            banner.setId(UUID.randomUUID().toString());
            banner.setCreateDate(new Date());
            map = crudMapper.add(banner, bannerDao, banner.getId());
        }
        if (oper.equals("edit")) {
            map = bannerService.edit(banner, bannerDao);
        }
        if (oper.equals("del")) {
            map = crudMapper.del(banner, bannerDao, banner.getId());
        }
        return map;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String id, MultipartFile cover, HttpSession httpSession) throws IOException, EncoderException {
        System.out.println("============================upload==============");
        System.out.println("id = " + id);
        Banner banner = new Banner();
        banner.setId(id);
        Map<String, Object> map = uploadUtil.uploadFile(httpSession, cover, "/banner/img");
        map.forEach((k, v) -> {
            if (k.equals("OriginalFilename")) {
                banner.setCover((String) v);
            }
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });

        //文件上传
        bannerService.edit(banner, bannerDao);
    }

}
