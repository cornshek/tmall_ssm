package org.shek.tmall.service;

import org.shek.tmall.pojo.Order;

import java.util.List;

public interface OrderService {

    //提供订单状态常量值
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order order);

    void delete(int id);

    void update(Order order);

    Order get(int id);

    List<Order> list();
}
