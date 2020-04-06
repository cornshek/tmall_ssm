package org.shek.tmall.service.impl;

import org.shek.tmall.mapper.PropertyValueMapper;
import org.shek.tmall.pojo.Product;
import org.shek.tmall.pojo.Property;
import org.shek.tmall.pojo.PropertyValue;
import org.shek.tmall.pojo.PropertyValueExample;
import org.shek.tmall.service.PropertyService;
import org.shek.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    @Override
    public void init(Product product) {
        List<Property> properties = propertyService.list(product.getCategoryId());

        for (Property property : properties) {
            PropertyValue propertyValue = get(property.getId(), product.getId());
            if (null == propertyValue) {
                propertyValue = new PropertyValue();
                propertyValue.setProductId(product.getId());
                propertyValue.setPropertyId(property.getId());
                propertyValueMapper.insert(propertyValue);
            }
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public PropertyValue get(int propertyId, int productId) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria()
                .andProductIdEqualTo(productId)
                .andPropertyIdEqualTo(propertyId);
        List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(example);
        if (propertyValues.isEmpty()) {
            return null;
        }
        return propertyValues.get(0);
    }

    @Override
    public List<PropertyValue> list(int productId) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria()
                .andProductIdEqualTo(productId);
        List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(example);
        for (PropertyValue propertyValue : propertyValues) {
            Property property = propertyService.get(propertyValue.getPropertyId());
            propertyValue.setProperty(property);
        }
        return propertyValues;
    }
}
