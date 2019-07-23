package com.baizhi.util;

import com.baizhi.util.service.ExtensionMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * @see
 */
@Service
public class ExtensionMapperImpl<T> implements ExtensionMapper<T> {
    /**
     * 封装的添加方法(扩展)
     *
     * @param args   需要的对象
     * @param mapper 调用mapper的一些方法
     * @see
     */

    @Override
    public Map<String, Object> add(T args, Mapper<T> mapper) {
        return null;
    }

    /**
     * 封装的修改方法(自己定义)
     *
     * @param args   需要的对象
     * @param mapper 调用mapper的一些方法
     *               status 可修改  message 可修改
     * @see
     */

    @Override
    public Map<String, Object> edit(T args, Mapper<T> mapper) {
        return null;
    }

    /**
     * 封装删除方法(扩展)
     *
     * @param args   需要的对象
     * @param mapper 调用mapper的一些方法
     *               status 可修改  message 可修改
     * @see
     */

    @Override
    public Map<String, Object> del(T args, Mapper<T> mapper) {
        return null;
    }


}
