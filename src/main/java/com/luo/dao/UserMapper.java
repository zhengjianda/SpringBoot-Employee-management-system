package com.luo.dao;

import com.luo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);
}
