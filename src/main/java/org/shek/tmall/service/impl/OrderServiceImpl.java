package org.shek.tmall.service.impl;

import org.shek.tmall.mapper.OrderMapper;
import org.shek.tmall.pojo.Order;
import org.shek.tmall.pojo.OrderExample;
import org.shek.tmall.pojo.User;
import org.shek.tmall.service.OrderService;
import org.shek.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> orders = orderMapper.selectByExample(example);

        //遍历查询出来的所有订单，设置User
        for (Order order : orders) {
            int userId = order.getUserId();
            User user = userService.get(userId);
            order.setUser(user);
        }
        return orders;
    }
}
