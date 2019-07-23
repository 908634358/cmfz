package com.baizhi.util.service;

import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * 封装了crud 的Map集合的方法
 */
public interface CrudMapper<T> {
    Map<String, Object> add(T args, Mapper<T> mapper, String id);

    Map<String, Object> edit(T args, Mapper<T> mapper, String id);

    Map<String, Object> del(T args, Mapper<T> mapper, String id);
}
