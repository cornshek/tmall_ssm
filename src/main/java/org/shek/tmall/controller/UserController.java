package org.shek.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shek.tmall.pojo.User;
import org.shek.tmall.service.UserService;
import org.shek.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("admin_user_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<User> userList = userService.list();

        int total = (int) new PageInfo<>(userList).getTotal();
        page.setTotal(total);

        model.addAttribute("userList", userList);
        model.addAttribute("page", page);

        return "admin/listUser";
    }
}
