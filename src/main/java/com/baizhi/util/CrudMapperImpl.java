package com.baizhi.util;

import com.baizhi.util.service.CrudMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.Map;


/**
 * 封装crud操作 为Map集合
 *       status 状态
 *       状态的信息
 * */
@Service
public class CrudMapperImpl<T> implements CrudMapper<T> {
    /**
     * 这是封装的添加方法
     *
     * @param args   需要的对象
     * @param mapper 调用mapper的一些方法
     * @param id     当前对象的Id
     *               如果id是需要通过雪花算法或UUID生成的需要自己设置
     */
    @Override
    public Map<String, Object> add(T args, Mapper<T> mapper, String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            mapper.insertSelective(args);
            map.put("status", true);
            map.put("message", id);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 这是封装的修改方法
     *
     * @param args   需要的对象
     * @param mapper 调用mapper的一些方法
     * @param id     当前对象的Id
     *               如果id是需要通过雪花算法或UUID生成的需要自己设置
     *               status 可修改  message 可修改
     */
    @Override
    public Map<String, Object> edit(T args, Mapper<T> mapper, String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            mapper.updateByPrimaryKeySelective(args);
            map.put("status", true);
            map.put("message", id);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    /**
     * 封装删除方法
     *
     * @param args   需要的对象
     * @param mapper 调用mapper的一些方法
     * @param id     当前对象的Id
     *               如果id是需要通过雪花算法或UUID生成的需要自己设置
     *               status 可修改  message 可修改
     */
    @Override
    public Map<String, Object> del(T args, Mapper<T> mapper, String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            mapper.deleteByPrimaryKey(args);
            map.put("status", true);
            map.put("message", id);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;

    }
}
