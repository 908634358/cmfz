package com.baizhi.controller;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import com.baizhi.util.service.CrudMapper;
import com.baizhi.util.service.SelectAllPagingMapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleDao articleDao;

    @Qualifier("selectAllPagingMapperImpl")
    @Autowired
    private SelectAllPagingMapper<Article> selectAllPaging;
    @Qualifier("crudMapperImpl")
    @Autowired
    private CrudMapper<Article> crudMapper;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/selectAllArticle")
    public Map<String, Object> getPage(Integer page, Integer rows, String guruId) {
        Article article = new Article();
        article.setGuruId(guruId);
        Map<String, Object> stringObjectMap = selectAllPaging.selectAllPaging(page, rows, articleDao, article);
        return stringObjectMap;
    }

    @RequestMapping("upload")
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile articleImage) {
        Map<String, Object> map = new HashMap<>();
//        文件上传
        try {
            articleImage.transferTo(new File(request.getSession().getServletContext().getRealPath("/article/img/"), articleImage.getOriginalFilename()));
            map.put("error", 0);
            map.put("url", "http://localhost:9090/cmfz/article/img/" + articleImage.getOriginalFilename());

//            http://localhost:8989/cmfz/image/"+articleImage.getOriginalFilename()
//            request获取
//            http://ip
//            端口
//            项目名


        } catch (IOException e) {
            map.put("error", 1);
        }
        return map;
    }

    @RequestMapping("/edit")
    public Map<String, Object> edit(String oper, Article article, String guruId) {
        System.out.println("guruId = " + guruId);
        System.out.println("oper = " + oper);
        System.out.println("article = " + article);

        Map<String, Object> map = new HashMap<>();
        if (oper.equals("add")) {
            map = articleService.add(article, articleDao);
        }
        if (oper.equals("edit")) {
            article.setGuruId(guruId);
            article.setCreateDate(new Date());
            map = crudMapper.edit(article, articleDao, article.getId());
        }
        if (oper.equals("del")) {
            map = crudMapper.del(article, articleDao, article.getId());
        }
        return map;
    }

    @RequestMapping("browser")
    public Map<String, Object> browser(HttpServletRequest request) {
        File file = new File(request.getSession().getServletContext().getRealPath("/article/img/"));
        File[] files = file.listFiles();

        Map<String, Object> map = new HashMap<>();
        //文件夹的网络路径
        map.put("current_url", "http://localhost:9090/cmfz/article/img/");
//        当前文件夹中的文件的数量
        map.put("total_count", files.length);
        ArrayList<Object> list = new ArrayList<>();
        for (File img : files) {
            HashMap<String, Object> imgObject = new HashMap<>();
            imgObject.put("is_dir", false);
            imgObject.put("has_file", false);
            imgObject.put("filesize", img.length());
            imgObject.put("is_photo", true);
            imgObject.put("filetype", FilenameUtils.getExtension(img.getName()));
            imgObject.put("filename", img.getName());
            imgObject.put("datetime", "2018-06-06 00:36:39");
            list.add(imgObject);
        }

        map.put("file_list", list);
        return map;
    }
/*文章的查询功能*/
    @RequestMapping("search")
    public List<com.baizhi.pojo.Article> search(String content) {
        System.out.println("content:" + content);
        List<com.baizhi.pojo.Article> articles = articleService.selectArticleByContent(content);
        for (com.baizhi.pojo.Article article : articles) {
            System.out.println(article);
        }
        return articles;
    }

}
