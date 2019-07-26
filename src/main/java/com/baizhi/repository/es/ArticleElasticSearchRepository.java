package com.baizhi.repository.es;

import com.baizhi.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleElasticSearchRepository extends ElasticsearchRepository<Article, String> {

}
