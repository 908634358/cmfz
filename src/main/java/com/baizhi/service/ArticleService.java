package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.util.service.ExtensionMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ArticleService extends ExtensionMapper<Article> {
    @Override
    Map<String, Object> add(Article args, Mapper<Article> mapper);

    @Override
    Map<String, Object> edit(Article args, Mapper<Article> mapper);

    @Override
    Map<String, Object> del(Article args, Mapper<Article> mapper);

    List<Article> selectAll();

    List<Article> selectArticleByGuruId(String guruId);
}
