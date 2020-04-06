package org.shek.tmall.mapper;

import java.util.List;
import org.shek.tmall.pojo.Order;
import org.shek.tmall.pojo.OrderExample;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}