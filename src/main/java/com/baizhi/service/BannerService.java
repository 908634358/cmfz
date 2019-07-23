package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.util.service.ExtensionMapper;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Transactional
public interface BannerService extends ExtensionMapper<Banner> {
    @Override
    Map<String, Object> add(Banner args, Mapper<Banner> mapper);

    @Override
    Map<String, Object> edit(Banner args, Mapper<Banner> mapper);

    @Override
    Map<String, Object> del(Banner args, Mapper<Banner> mapper);

    List<Banner> selectAll();

}
