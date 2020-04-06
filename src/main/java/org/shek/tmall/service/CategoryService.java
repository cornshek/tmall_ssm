package org.shek.tmall.service;

import org.shek.tmall.pojo.Category;
import org.shek.tmall.util.Page;

import java.util.List;

public interface CategoryService {
    List<Category> list();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}
