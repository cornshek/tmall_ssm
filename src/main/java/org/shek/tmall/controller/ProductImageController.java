package org.shek.tmall.controller;

import org.shek.tmall.pojo.Product;
import org.shek.tmall.pojo.ProductImage;
import org.shek.tmall.service.ProductImageService;
import org.shek.tmall.service.ProductService;
import org.shek.tmall.util.ImageUtil;
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
public class ProductImageController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    @RequestMapping("admin_productImage_list")
    public String list(int productId, Model model) {
        Product product = productService.get(productId);
        List<ProductImage> singleProductImages = productImageService.list(productId, ProductImageService.typeSingle);
        List<ProductImage> detailProductImages = productImageService.list(productId, ProductImageService.typeDetail);

        model.addAttribute("product", product);
        model.addAttribute("singleProductImages", singleProductImages);
        model.addAttribute("detailProductImages", detailProductImages);

        return "admin/listProductImage";
    }

    @RequestMapping("admin_productImage_add")
    public String add(ProductImage productImage, HttpSession session, MultipartFile image) throws IOException {

        productImageService.add(productImage);
        String fileName = productImage.getId() + ".jpg";
        String imageFolder;
        String imageFolderSmall = null;
        String imageFolderMiddle = null;

        if (productImageService.typeSingle.equals(productImage.getType())) {
            imageFolder = session.getServletContext()
                    .getRealPath("img/productSingle");
            imageFolderSmall = session.getServletContext()
                    .getRealPath("img/productSingle_small");
            imageFolderMiddle = session.getServletContext()
                    .getRealPath("img/productSingle_middle");
        } else {
            imageFolder = session.getServletContext()
                    .getRealPath("img/productDetail");
        }

        File file = new File(imageFolder, fileName);
        file.getParentFile().mkdirs();

        image.transferTo(file);
        BufferedImage bufferedImage = ImageUtil.changeToJpg(file);
        ImageIO.write(bufferedImage, "jpg", file);

        if (ProductImageService.typeSingle.equals(productImage.getType())) {
            File fileSmall = new File(imageFolderSmall, fileName);
            File fileMiddle = new File(imageFolderMiddle, fileName);

            ImageUtil.resizeImage(file, 56, 56, fileSmall);
            ImageUtil.resizeImage(file, 217, 190, fileMiddle);
        }
        return "redirect:admin_productImage_list?productId=" + productImage.getProductId();
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(int id, HttpSession session) {
        ProductImage productImage = productImageService.get(id);

        String fileName = productImage.getId() + ".jpg";
        String imageFolder;
        String imageFolderSmall = null;
        String imageFolderMiddle = null;

        if (ProductImageService.typeSingle.equals(productImage.getType())) {
            imageFolder = session.getServletContext()
                    .getRealPath("img/productSingle");
            imageFolderSmall = session.getServletContext()
                    .getRealPath("img/productSingle_small");
            imageFolderMiddle = session.getServletContext()
                    .getRealPath("img/productSingle_middle");
            File imageFile = new File(imageFolder, fileName);
            File fileSmall = new File(imageFolderSmall, fileName);
            File fileMiddle = new File(imageFolderMiddle, fileName);
            imageFile.delete();
            fileSmall.delete();
            fileMiddle.delete();
        } else {
            imageFolder = session.getServletContext()
                    .getRealPath("img/productDetail");
            File imageFile = new File(imageFolder, fileName);
            imageFile.delete();
        }

        productImageService.delete(id);

        return "redirect:admin_productImage_list?productId=" + productImage.getProductId();
    }
}
