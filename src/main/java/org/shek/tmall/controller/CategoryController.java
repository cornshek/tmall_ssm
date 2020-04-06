package org.shek.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.shek.tmall.pojo.Category;
import org.shek.tmall.service.CategoryService;
import org.shek.tmall.util.ImageUtil;
import org.shek.tmall.util.Page;
import org.shek.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Category> categories = categoryService.list();
        int total = (int) new PageInfo<>(categories).getTotal();
        page.setTotal(total);
        model.addAttribute("categories", categories);
//        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    //参数 Category category接受页面提交的分类名称
    //参数 session 用于在后续获取当前应用的路径
    //UploadedImageFile 用于接受上传的图片
    @RequestMapping("admin_category_add")
    public String add(Category category, HttpSession session,
                      UploadedImageFile uploadedImageFile)
            throws IOException {
        categoryService.add(category);
        File imageFolder = new File(session.getServletContext().
                getRealPath("img/category"));
        File file = new File(imageFolder, category.getId() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage image = ImageUtil.changeToJpg(file);
        ImageIO.write(image, "jpg", file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id, HttpSession session) {
        categoryService.delete(id);

        File imageFolder = new File(session.getServletContext().
                getRealPath("img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(int id, Model model) {
        Category category = categoryService.get(id);
        model.addAttribute(("category"), category);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category category, HttpSession session,
                         UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.update(category);
        MultipartFile image = uploadedImageFile.getImage();
        if (null != image && !image.isEmpty()) {
            File imageFolder = new File(session.getServletContext().
                    getRealPath("img/category"));
            File file = new File(imageFolder, category.getId() + ".jpg");
            file.delete();
            image.transferTo(file);
            BufferedImage img = ImageUtil.changeToJpg(file);
            ImageIO.write((img), "jpg", file);
        }
        return "redirect:/admin_category_list";
    }
}
