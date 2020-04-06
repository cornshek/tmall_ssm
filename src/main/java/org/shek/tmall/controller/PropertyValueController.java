package org.shek.tmall.controller;

import org.shek.tmall.pojo.Product;
import org.shek.tmall.pojo.PropertyValue;
import org.shek.tmall.service.ProductService;
import org.shek.tmall.service.PropertyService;
import org.shek.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model, int productId) {
        //初始化产品属性值对象
        Product product = productService.get(productId);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(productId);

        model.addAttribute("product", product);
        model.addAttribute("propertyValues", propertyValues);

        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue) {
        propertyValueService.update(propertyValue);
        return "success";
    }
}
