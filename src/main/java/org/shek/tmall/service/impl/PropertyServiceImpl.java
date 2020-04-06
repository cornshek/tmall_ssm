package org.shek.tmall.service.impl;

import org.shek.tmall.mapper.PropertyMapper;
import org.shek.tmall.pojo.Property;
import org.shek.tmall.pojo.PropertyExample;
import org.shek.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public void add(Property property) {
        propertyMapper.insert(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKeySelective(property);
    }

    @Override
    public Property get(int id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int categoryId) {
        PropertyExample example = new PropertyExample();
        example.createCriteria()
                .andCategoryIdEqualTo(categoryId);
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }
}
