package org.shek.tmall.mapper;

import java.util.List;
import org.shek.tmall.pojo.User;
import org.shek.tmall.pojo.UserExample;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}