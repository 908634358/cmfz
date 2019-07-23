package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AlbumDao extends Mapper<Album> {

}
