package org.shek.tmall.service.impl;

import org.shek.tmall.mapper.ProductImageMapper;
import org.shek.tmall.pojo.ProductImage;
import org.shek.tmall.pojo.ProductImageExample;
import org.shek.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int productId, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria()
                .andProductIdEqualTo(productId)
                .andTypeEqualTo(type);
        example.setOrderByClause("id desc");
        return productImageMapper.selectByExample(example);
    }
}
