package com.baizhi.service.serviceImpl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Article;
import com.baizhi.entity.Guru;
import com.baizhi.repository.es.ArticleElasticSearchRepository;
import com.baizhi.service.ArticleService;
import io.goeasy.GoEasy;
import org.apache.commons.collections4.IterableUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleElasticSearchRepository articleRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private GuruDao guruDao;
    /* 添加文章后推送文章，并将添加的文章信息保存到ES中*/
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
            // 获取上师
            Guru guru = guruDao.selectByPrimaryKey(article.getGuruId());
            //将上师的name装入一个Article对象中
            com.baizhi.pojo.Article article1 = new com.baizhi.pojo.Article(article.getId(), article.getTitle(), article.getContent(), article.getCreateDate(), guru.getName());
            // 调用ESRepository的添加方法
            articleRepository.save(article1);
            System.out.println("article1 = " + article1);
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

/*ES的高亮查询*/
    @Override
    public List<com.baizhi.pojo.Article> selectArticleByContent(String content) {
        //es代码
        if ("".equals(content) || content == null) {
            Iterable<com.baizhi.pojo.Article> all = articleRepository.findAll();
            List<com.baizhi.pojo.Article> articles = IterableUtils.toList(all);
            return articles;
        } else {

            HighlightBuilder.Field highlightBuilder = new HighlightBuilder
                    .Field("*")
                    .preTags("<span style='color:red'>")
                    .postTags("</span>")
                    .requireFieldMatch(false); //？

            NativeSearchQuery query = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.queryStringQuery(content).field("title").field("guruId").field("content"))
                    .withSort(SortBuilders.scoreSort())
                    .withHighlightFields(highlightBuilder)
                    .build();


            AggregatedPage<com.baizhi.pojo.Article> articles = elasticsearchTemplate.queryForPage(query, com.baizhi.pojo.Article.class, new SearchResultMapper() {
                @Override
                public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
                    SearchHits responseHits = response.getHits();
                    SearchHit[] hits = responseHits.getHits();
                    List<com.baizhi.pojo.Article> list = new ArrayList<>();
                    for (SearchHit hit : hits) {
                        com.baizhi.pojo.Article article = new com.baizhi.pojo.Article();
                        Map<String, Object> map = hit.getSourceAsMap();
                        article.setId(map.get("id").toString());
                        article.setTitle(map.get("title").toString());
                        article.setAuthor(map.get("author").toString());
                        article.setContent(map.get("content").toString());
                        String date = map.get("createDate").toString();
                        article.setCreateDate(new Date(Long.valueOf(date)));

//                        处理高亮
                        Map<String, HighlightField> map1 = hit.getHighlightFields();
                        if (map1.get("title") != null) {
                            article.setTitle(map1.get("title").getFragments()[0].toString());
                        }
                        if (map1.get("author") != null) {
                            article.setAuthor(map1.get("author").getFragments()[0].toString());
                        }
                        if (map1.get("content") != null) {
                            article.setContent(map1.get("content").getFragments()[0].toString());
                        }
                        list.add(article);
                    }
                    return new AggregatedPageImpl<T>((List<T>) list);
                }
            });
            List<com.baizhi.pojo.Article> list = articles.getContent();
            return list;
        }
    }


}
