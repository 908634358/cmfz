package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.util.service.ExtensionMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface AlbumService extends ExtensionMapper<Album> {
    @Override
    Map<String, Object> add(Album args, Mapper<Album> mapper);

    @Override
    Map<String, Object> edit(Album args, Mapper<Album> mapper);

    @Override
    Map<String, Object> del(Album args, Mapper<Album> mapper);

    List<Album> selectAll();
}
