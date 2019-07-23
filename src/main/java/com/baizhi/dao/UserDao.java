package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.pojo.City;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserDao extends Mapper<User> {

    Integer selectAllFirstHalfYear(@Param("count") Integer count);

    List<City> selectAllCountBySex(@Param("sex") String sex);

}
