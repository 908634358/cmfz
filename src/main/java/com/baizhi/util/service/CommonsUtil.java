package com.baizhi.util.service;

/**
 * CommonsUtilService 这个工具类是基于通用Mapper实现的，需要通用Mapper环境
 * 查询所有的分页方法
 * 增删改查 (可以自定义)方法
 */
public interface CommonsUtil<T> extends CrudMapper<T>, SelectAllPagingMapper<T> {
    // 查询所有的分页方法

}
