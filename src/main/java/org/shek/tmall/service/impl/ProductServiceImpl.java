package org.shek.tmall.service.impl;

import org.shek.tmall.mapper.ProductMapper;
import org.shek.tmall.pojo.Category;
import org.shek.tmall.pojo.Product;
import org.shek.tmall.pojo.ProductExample;
import org.shek.tmall.pojo.ProductImage;
import org.shek.tmall.service.CategoryService;
import org.shek.tmall.service.ProductImageService;
import org.shek.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);

        //为product对象设置对应的category
        Category category = categoryService.get(product.getCategoryId());
        product.setCategory(category);

        //为product对象设置对应的productImage
        setFirstProductImage(product);

        return product;
    }

    @Override
    public List list(int categoryId) {
        ProductExample example = new ProductExample();
        example.createCriteria()
                .andCategoryIdEqualTo(categoryId);
        example.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(example);

        //为product对象设置对应的category
        for (Product product : products) {
            Category category = categoryService.get(product.getCategoryId());
            product.setCategory(category);
        }

        //为product对象设置对应的productImage
        for (Product product : products) {
            setFirstProductImage(product);
        }

        return products;
    }

    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> productImages = productImageService
                .list(product.getId(), productImageService.typeSingle);
        if (!productImages.isEmpty()) {
            ProductImage productImage = productImages.get(0);
            product.setProductImage(productImage);
        }
    }
}
