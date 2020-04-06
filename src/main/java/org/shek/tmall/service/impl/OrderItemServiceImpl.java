package org.shek.tmall.service.impl;

import org.aspectj.weaver.ast.Or;
import org.shek.tmall.mapper.OrderItemMapper;
import org.shek.tmall.pojo.Order;
import org.shek.tmall.pojo.OrderItem;
import org.shek.tmall.pojo.OrderItemExample;
import org.shek.tmall.pojo.Product;
import org.shek.tmall.service.OrderItemService;
import org.shek.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
        orderItem.setProduct(productService.get(orderItem.getProductId()));
        return orderItem;
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id  desc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public void fill(List<Order> orders) {
        //遍历每一个订单，调用fill
        for (Order order : orders) {
            fill(order);
        }
    }

    @Override
    public void fill(Order order) {
        OrderItemExample example = new OrderItemExample();

        //根据Order的ID查出所有的OrderItem
        example.createCriteria()
                .andOrderIdEqualTo(order.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);

        //给每个一OrderItem设置Product
        //计算总金额和总数量,并设置到Order上
        float totalSum = 0;
        int totalNumber = 0;
        for (OrderItem orderItem : orderItems) {
            Product product = productService.get(orderItem.getProductId());
            orderItem.setProduct(product);

            totalSum += orderItem.getProduct().getPromotePrice()*orderItem.getNumber();
            totalNumber += orderItem.getNumber();
        }
        order.setTotalSum(totalSum);
        order.setTotalNumber(totalNumber);

        //将所有OrderItem设置到Order的OrderItems属性上
        order.setOrderItems(orderItems);


    }
}
