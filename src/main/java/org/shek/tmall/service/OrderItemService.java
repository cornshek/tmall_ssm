package org.shek.tmall.service;

import org.shek.tmall.pojo.Order;
import org.shek.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(OrderItem orderItem);

    void delete(int id);

    void update(OrderItem orderItem);

    OrderItem get(int id);

    List list();

    //实现Order与OrderItem的一对多
    void fill(List<Order> orders);

    void fill(Order order);
}
