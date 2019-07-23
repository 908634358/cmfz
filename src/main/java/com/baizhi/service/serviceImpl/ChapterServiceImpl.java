package com.baizhi.service.serviceImpl;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChapterServiceImpl implements ChapterService {


    @Override
    public Map<String, Object> add(Chapter args, Mapper<Chapter> mapper) {
        return null;
    }

    @Override
    public Map<String, Object> edit(Chapter args, Mapper<Chapter> mapper) {
        Map<String, Object> map = new HashMap<>();
        try {
            if ("".equals(args.getName())) {
                args.setName(null);
            }
            // 如果 cover 的值是string就将其设置为null,防止修改
            mapper.updateByPrimaryKeySelective(args);
            map.put("status", true);
            map.put("message", args.getId());
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> del(Chapter args, Mapper<Chapter> mapper) {
        return null;
    }
}
