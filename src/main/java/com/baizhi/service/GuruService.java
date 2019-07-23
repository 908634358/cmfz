package com.baizhi.service;

import com.baizhi.entity.Guru;
import com.baizhi.util.service.ExtensionMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface GuruService extends ExtensionMapper<Guru> {
    @Override
    Map<String, Object> add(Guru args, Mapper<Guru> mapper);

    @Override
    Map<String, Object> edit(Guru args, Mapper<Guru> mapper);

    @Override
    Map<String, Object> del(Guru args, Mapper<Guru> mapper);
}
