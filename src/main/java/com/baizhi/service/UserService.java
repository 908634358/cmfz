package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.util.service.ExtensionMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface UserService extends ExtensionMapper<User> {
    @Override
    Map<String, Object> add(User args, Mapper<User> mapper);

    @Override
    Map<String, Object> edit(User args, Mapper<User> mapper);

    @Override
    Map<String, Object> del(User args, Mapper<User> mapper);

    User selectUserById(String guruId);
}
