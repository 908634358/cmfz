package com.baizhi.service.serviceImpl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;


    @Override
    public Map<String, Object> add(Article article, Mapper<Article> mapper) {
        System.out.println("add                article = " + article);
        Map<String, Object> map = new HashMap<>();
        article.setId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        int i = articleDao.insertSelective(article);
        if (i == 0) {
            throw new RuntimeException("添加文章失败");
        } else {
            //第一个参数：REST Host       第二个参数：appks
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-268c25f44f5841ff93a8f4e6aa24193a");
//        第一个参数：channel的名称      第二个参数：发送的内容
            goEasy.publish("cmfz-article", article.getContent());
        }
        return map;
    }

    @Override
    public Map<String, Object> edit(Article args, Mapper<Article> mapper) {
        return null;
    }

    @Override
    public Map<String, Object> del(Article args, Mapper<Article> mapper) {
        return null;
    }

    @Override
    public List<Article> selectAll() {
        return articleDao.selectAll();
    }

    @Override
    public List<Article> selectArticleByGuruId(String guruId) {
        Article article = new Article();
        article.setGuruId(guruId);
        List<Article> list = articleDao.select(article);
        return list;
    }
}
