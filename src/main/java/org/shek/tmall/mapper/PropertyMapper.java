package org.shek.tmall.mapper;

import java.util.List;
import org.shek.tmall.pojo.Property;
import org.shek.tmall.pojo.PropertyExample;

public interface PropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Property record);

    int insertSelective(Property record);

    List<Property> selectByExample(PropertyExample example);

    Property selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);
}