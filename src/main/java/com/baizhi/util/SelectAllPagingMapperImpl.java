package com.baizhi.util;

import com.baizhi.util.service.SelectAllPagingMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询所有的分页方法
 */
@Service
public class SelectAllPagingMapperImpl<T> implements SelectAllPagingMapper<T> {
    /**
     * 此方法是分页查询所有使用通用Mapper封装
     * Integer page, 页数
     * Integer rows, 当前页中的条数
     * Mapper mapper, 使用一个类继承mapper就可以使用Mapper的所有方法
     * Object args 所有对象的父类，查询当前页数需要的参数，查询当前对象在数据库有多少条需要的对象
     */
    @Override
    public Map<String, Object> selectAllPaging(Integer page, Integer rows, Mapper<T> mapper, T args) {
        System.out.println("page = " + page);
        System.out.println("rows = " + rows);
        System.out.println("mapper = " + mapper);
        System.out.println("args = " + args);
        Map<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<T> banners = mapper.selectByRowBounds(args, rowBounds);
        int count = mapper.selectCount(args);
        map.put("page", page);       //当前页码数值
        map.put("rows", banners);    //当前页中的banner数据
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1); //总页数
        map.put("records", count);   //总条数
        return map;
    }

}
