package org.shek.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shek.tmall.pojo.Order;
import org.shek.tmall.service.OrderItemService;
import org.shek.tmall.service.OrderService;
import org.shek.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("admin_order_list")
    public String list(Model model, Page page) {

        //获取分页对象，查询订单集合
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Order> orders = orderService.list();

        //获取订单总数并设置在分页对象上
        int total = (int) new PageInfo<>(orders).getTotal();
        page.setTotal(total);

        //用orderItemService的fill方法，为orders填充orderItems
        orderItemService.fill(orders);

        model.addAttribute("orders", orders);
        model.addAttribute("page", page);

        return "admin/listOrder";
    }

    @RequestMapping("admin_order_delivery")
    public String delivery(Order order) {
        order.setDeliverydate(new Date());
        order.setStatus(orderService.waitConfirm);
        orderService.update(order);

        return "redirect:admin_order_list";
    }
}
