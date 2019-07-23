package com.baizhi.util.service;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * 封装的添加方法(扩展)
 * 封装的修改方法(扩展)
 * 封装的删除方法(扩展)
 */
@Repository
public interface ExtensionMapper<T> {
    Map<String, Object> add(T args, Mapper<T> mapper);

    Map<String, Object> edit(T args, Mapper<T> mapper);

    Map<String, Object> del(T args, Mapper<T> mapper);
}
