package com.baizhi.controller;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
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
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ChapterDao chapterDao;

    @Qualifier("selectAllPagingMapperImpl")
    @Autowired
    private SelectAllPagingMapper<Chapter> selectAllPaging;
    @Qualifier("crudMapperImpl")
    @Autowired
    private CrudMapper<Chapter> crudMapper;
    @Autowired
    private UploadUtil uploadUtil;

    @RequestMapping("/selectChaptersBuAlbumId")
    @ResponseBody
    public Map<String, Object> getPage(Integer page, Integer rows, String albumId) {
        System.out.println("albumId = " + albumId);
        System.out.println("page = " + page);
        System.out.println("rows = " + rows);
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        Map<String, Object> stringObjectMap = selectAllPaging.selectAllPaging(page, rows, chapterDao, chapter);
        return stringObjectMap;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String, Object> editBanner(String oper, Chapter chapter, String albumId) {
        System.out.println("albumId = " + albumId);
        System.out.println(oper);
        System.out.println("chapter = " + chapter);

        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            chapter.setId(UUID.randomUUID().toString());
            chapter.setCreateDate(new Date());
            chapter.setAlbumId(albumId);
            map = crudMapper.add(chapter, chapterDao, chapter.getId());
        }
        if (oper.equals("edit")) {
            chapter.setAlbumId(albumId);
            map = chapterService.edit(chapter, chapterDao);
        }
        if (oper.equals("del")) {
            map = crudMapper.del(chapter, chapterDao, chapter.getId());
        }
        return map;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String id, MultipartFile name, HttpSession httpSession) throws IOException, EncoderException {
        Chapter chapter = new Chapter();
        chapter.setId(id);
        Map<String, Object> map = uploadUtil.uploadFile(httpSession, name, "album/music");
        map.forEach((k, v) -> {
            if (k.equals("OriginalFilename")) {
                chapter.setName((String) v);
            }
            if (k.equals("Duration")) {
                chapter.setDuration((String) v);
            }
            if (k.equals("bigDecimal")) {
                chapter.setSize(v + "MB");
            }
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });

        chapterService.edit(chapter, chapterDao);
    }

}
