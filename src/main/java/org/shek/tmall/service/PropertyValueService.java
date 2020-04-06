package org.shek.tmall.service;

import org.shek.tmall.pojo.Product;
import org.shek.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {

    void init(Product product);

    void update(PropertyValue propertyValue);

    PropertyValue get(int propertyId, int productId);

    List<PropertyValue> list(int productId);
}
