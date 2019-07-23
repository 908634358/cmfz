package com.baizhi.service.serviceImpl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map<String, Object> add(Album args, Mapper<Album> mapper) {
        return null;
    }

    @Override
    public Map<String, Object> edit(Album args, Mapper<Album> mapper) {
        Map<String, Object> map = new HashMap<>();
        try {
            if ("".equals(args.getCover())) {
                args.setCover(null);
            }
            // 如果 cover 的值是string就将其设置为null,防止修改
            albumDao.updateByPrimaryKeySelective(args);
            map.put("status", true);
            map.put("message", args.getId());
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> del(Album args, Mapper<Album> mapper) {
        return null;
    }

    @Override
    public List<Album> selectAll() {
        return albumDao.selectAll();
    }

}
