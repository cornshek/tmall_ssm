package org.shek.tmall.mapper;

import java.util.List;
import org.shek.tmall.pojo.Review;
import org.shek.tmall.pojo.ReviewExample;

public interface ReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    List<Review> selectByExample(ReviewExample example);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}