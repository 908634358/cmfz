package com.baizhi.service;

import com.baizhi.entity.Chapter;
import com.baizhi.util.service.ExtensionMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface ChapterService extends ExtensionMapper<Chapter> {

    @Override
    Map<String, Object> add(Chapter args, Mapper<Chapter> mapper);

    @Override
    Map<String, Object> edit(Chapter args, Mapper<Chapter> mapper);

    @Override
    Map<String, Object> del(Chapter args, Mapper<Chapter> mapper);
}
