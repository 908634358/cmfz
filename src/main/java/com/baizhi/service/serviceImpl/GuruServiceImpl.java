package com.baizhi.service.serviceImpl;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class GuruServiceImpl implements GuruService {

    @Override
    public Map<String, Object> add(Guru args, Mapper<Guru> mapper) {
        return null;
    }

    @Override
    public Map<String, Object> edit(Guru guru, Mapper<Guru> mapper) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 如果 cover 的值是string就将其设置为null,防止修改
            mapper.updateByPrimaryKeySelective(guru);
            map.put("status", true);
            map.put("message", guru.getId());
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> del(Guru args, Mapper<Guru> mapper) {
        return null;
    }
}
