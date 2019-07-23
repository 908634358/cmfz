package com.baizhi.service.serviceImpl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> add(User args, Mapper<User> mapper) {
        return null;
    }

    @Override
    public Map<String, Object> edit(User args, Mapper<User> mapper) {
        Map<String, Object> map = new HashMap<>();
        try {
            if ("".equals(args.getPhoto())) {
                args.setPhoto(null);
            }
            // 如果 cover 的值是string就将其设置为null,防止修改
            userDao.updateByPrimaryKeySelective(args);
            map.put("status", true);
            map.put("message", args.getId());
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> del(User args, Mapper<User> mapper) {
        return null;
    }

    @Override
    public User selectUserById(String guruId) {
        User user = new User();
        user.setGuruId(guruId);

        return userDao.selectOne(user);
    }

}
