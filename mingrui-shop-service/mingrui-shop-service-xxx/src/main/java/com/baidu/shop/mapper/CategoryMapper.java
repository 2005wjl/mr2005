package com.baidu.shop.mapper;

import com.baidu.shop.entity.CategoryEntity;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<CategoryEntity> {

    @Select(value = "select id,name from tb_category where id in (select category_id from tb_category_brand where brand_id=#{brandId})")
    List<CategoryEntity> getCategoryByBrandId(Integer brandId);

}

