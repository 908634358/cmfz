package com.baizhi.port;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.User;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/first_page")
public class FirstPageController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    /**
     * @param uid      用户唯一标识
     * @param type     请求数据类型（首页：all，闻：wen，思：si）
     * @param sub_type 上师言教：ssyj，显密法要：xmfy
     * @return map
     */
    public Map<String, Object> first(String uid, String type, String sub_type) {
        HashMap<String, Object> map = new HashMap<>();
        // 查询首页
        if ("all".equals(type)) {
            //查询轮播图
            List<Banner> banners = bannerService.selectAll();
            map.put("header", banners);
            //查询专辑
            List<Album> albums = albumService.selectAll();
            map.put("album", albums);
            //查询文章
            User user = userService.selectUserById(uid);
            String dharma = user.getGuruId();
            List<Article> articles = articleService.selectArticleByGuruId(dharma);
            map.put("artical", articles);
        }
        // 查询专辑(闻)
        if ("wen".equals(type)) {
            //获取闻（专辑）当中的数据
            //            查专辑
            List<Album> albums = albumService.selectAll();
            map.put("album", albums);
        }
        // 查询 文章
        if ("si".equals(type)) {
            //获取思(文章)中的数据
            if ("ssyi".equals(sub_type)) {
                //查询上师言教
                User user = userService.selectUserById(uid);
                String dharma = user.getGuruId();
                List<Article> articles = articleService.selectArticleByGuruId(dharma);
                System.out.println("articles = " + articles);
                map.put("artical", articles);
            }
            //查询上师言教
            if ("xmfy".equals(sub_type)) {
                List<Article> articles = articleService.selectAll();
                map.put("artical", articles);
            }
        }

        return map;
    }
}
