package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ArticleDao extends Mapper<Article> {

}
