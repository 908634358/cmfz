package com.baizhi.util.service;

import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface SelectAllPagingMapper<T> {
    Map<String, Object> selectAllPaging(Integer page, Integer rows, Mapper<T> mapper, T args);
}
