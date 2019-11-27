package com.huoli.mapper;

import com.huoli.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    List<User> selectUser();

}
