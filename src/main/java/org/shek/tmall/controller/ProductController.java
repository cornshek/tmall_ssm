package org.shek.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shek.tmall.pojo.Category;
import org.shek.tmall.pojo.Product;
import org.shek.tmall.service.CategoryService;
import org.shek.tmall.service.ProductService;
import org.shek.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_product_list")
    public String list(int categoryId, Model model, Page page) {

        Category category = categoryService.get(categoryId);

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Product> products = productService.list(categoryId);

        int total = (int) new PageInfo<>(products).getTotal();
        page.setTotal(total);
        page.setParam("&categoryId=" + categoryId);

        model.addAttribute("products", products);
        model.addAttribute("page", page);
        model.addAttribute("category", category);
        return "admin/listProduct";
    }

    @RequestMapping("admin_product_add")
    public String add(Product product) {
        product.setCreateDate(new Date());
        productService.add(product);
        return "redirect:admin_product_list?categoryId=" + product.getCategoryId();
    }

    @RequestMapping("admin_product_edit")
    public String edit(Model model, int id) {
        Product product = productService.get(id);
        model.addAttribute("product", product);

        return "admin/editProduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Product product) {
        productService.update(product);
        return "redirect:admin_product_list?categoryId=" + product.getCategoryId();
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id) {
        Product product = productService.get(id);
        productService.delete(id);
        return "redirect:admin_product_list?categoryId=" + product.getCategoryId();
    }
}
