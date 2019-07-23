package com.baizhi.controller;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.util.service.CrudMapper;
import com.baizhi.util.service.SelectAllPagingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumDao albumDao;

    @Qualifier("selectAllPagingMapperImpl")
    @Autowired
    private SelectAllPagingMapper<Album> selectAllPaging;
    @Qualifier("crudMapperImpl")
    @Autowired
    private CrudMapper<Album> crudMapper;

    @RequestMapping("/selectAllAlbum")
    @ResponseBody
    public Map<String, Object> getPage(Integer page, Integer rows) {
        System.out.println("page = " + page);
        System.out.println("rows = " + rows);
        Map<String, Object> map = selectAllPaging.selectAllPaging(page, rows, albumDao, new Album());
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, Object> editBanner(String oper, Album album) {

        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            album.setId(UUID.randomUUID().toString());
            album.setCreateDate(new Date());
            map = crudMapper.add(album, albumDao, album.getId());
        }
        if (oper.equals("edit")) {
            map = albumService.edit(album, albumDao);
        }
        if (oper.equals("del")) {
            map = crudMapper.del(album, albumDao, album.getId());
        }
        return map;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String id, MultipartFile cover, HttpSession httpSession) throws IOException {
        System.out.println("cover = " + cover);
        System.out.println("id:" + id);
//        文件上传
        cover.transferTo(new File(httpSession.getServletContext().getRealPath("/album/img"), cover.getOriginalFilename()));
        System.out.println("cover upload over!");
//        根据id修改图片的名字
        Album album = new Album();
        album.setId(id);
        album.setCover(cover.getOriginalFilename());
        System.out.println("banner = " + album);
        albumService.edit(album, albumDao);
    }

}
