package org.shek.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shek.tmall.pojo.Category;
import org.shek.tmall.pojo.Property;
import org.shek.tmall.service.CategoryService;
import org.shek.tmall.service.PropertyService;
import org.shek.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_list")
    public String list(int categoryId, Model model, Page page) {
        Category category = categoryService.get(categoryId);

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Property> properties = propertyService.list(categoryId);

        int total = (int) new PageInfo<>(properties).getTotal();
        page.setTotal(total);
        page.setParam("&categoryId=" + categoryId);

        model.addAttribute("properties", properties);
        model.addAttribute("category", category);
        model.addAttribute("page", page);

        return "admin/listProperty";
    }

    @RequestMapping("admin_property_add")
    public String add(Property property) {
        propertyService.add(property);
        return "redirect:admin_property_list?categoryId=" + property.getCategoryId();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model, int id) {
        Property property = propertyService.get(id);
        Category category = categoryService.get(property.getCategoryId());
        model.addAttribute("property", property);
        model.addAttribute("category", category);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property property) {
        propertyService.update(property);
        return "redirect:admin_property_list?categoryId=" + property.getCategoryId();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id) {
        Property property = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?categoryId=" + property.getCategoryId();
    }
}
