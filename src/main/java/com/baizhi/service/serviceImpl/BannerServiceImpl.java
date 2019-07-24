package com.baizhi.service.serviceImpl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Map<String, Object> add(Banner args, Mapper<Banner> mapper) {
        return null;
    }

    @Override
    public Map<String, Object> edit(Banner banner, Mapper<Banner> mapper) {
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println("".equals(banner.getCover()));
            if ("".equals(banner.getCover())) {
                banner.setCover(null);
            }
            mapper.updateByPrimaryKeySelective(banner);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> del(Banner args, Mapper<Banner> mapper) {
        Map<String, Object> map = new HashMap<>();
        try {
            mapper.deleteByPrimaryKey(args);
            map.put("status", true);
            map.put("message", args.getId());
            redisTemplate.opsForHash().delete("BannerDel", map);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;

    }

    @Override
    public List<Banner> selectAll() {

        return bannerDao.selectAll();
    }

}
