package org.shek.tmall.mapper;

import java.util.List;
import org.shek.tmall.pojo.Product;
import org.shek.tmall.pojo.ProductExample;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}