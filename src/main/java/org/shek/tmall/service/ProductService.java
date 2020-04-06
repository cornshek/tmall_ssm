package org.shek.tmall.service;

import org.shek.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);

    void delete(int id);

    void update(Product product);

    Product get(int id);

    List list(int categoryId);

    void setFirstProductImage(Product product);
}
