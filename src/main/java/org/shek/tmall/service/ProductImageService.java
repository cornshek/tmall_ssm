package org.shek.tmall.service;

import org.shek.tmall.pojo.Product;
import org.shek.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {

    String typeSingle = "type_single";
    String typeDetail = "type_detail";

    void add(ProductImage productImage);

    void delete(int id);

    void update(ProductImage productImage);

    ProductImage get(int id);

    List list(int productId, String type);
}
