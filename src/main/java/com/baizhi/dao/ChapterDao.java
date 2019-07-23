package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ChapterDao extends Mapper<Chapter> {

}
