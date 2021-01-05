package com.baidu.shop.service;

import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryBrandEntity;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.mapper.CategoryBrandMapper;
import com.baidu.shop.mapper.CategoryMapper;
import com.baidu.shop.utils.ObjectUtil;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
public class CategoryServiceImpl extends BaseApiService implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public Result<List<CategoryEntity>> getCategoryByBrandId(Integer brandId) {

        List<CategoryEntity>  list = categoryMapper.getCategoryByBrandId(brandId);

        return this.setResultSuccess(list);
    }


    //新增
    @Override
    @Transactional
    public Result<JsonObject> addCargroy(CategoryEntity categoryEntity) {

        CategoryEntity parentEntity = new CategoryEntity();
        parentEntity.setId(categoryEntity.getParentId());
        parentEntity.setIsParent(1);
        categoryMapper.updateByPrimaryKeySelective(parentEntity);

        categoryMapper.insertSelective(categoryEntity);
        return this.setResultSuccess("新增成功");
    }

    //修改
    @Override
    @Transactional

    public Result<JsonObject> enitCatgory(CategoryEntity categoryEntity) {

        categoryMapper.updateByPrimaryKeySelective(categoryEntity);

        return this.setResultSuccess("修改成功");
    }

    //查询
    @Override
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid) {

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setParentId(pid);

        List<CategoryEntity> list = categoryMapper.select(categoryEntity);

        return this.setResultSuccess(list);
    }

    //删除
    @Override
    @Transactional
    public Result<JsonObject> delCategroy(Integer id) {

        //判断id是否合法,如果不合法直接结束
        if(ObjectUtil.isNull(id) || id <= 0) return this.setResultError("id操作不正确");

        //根据id查询数据
        CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(id);

        //判断根据id查询出来的数据正不正确,如果不正确直接结束
        if(ObjectUtil.isNull(categoryEntity)) return this.setResultError("数据不正确");

        //如果不正确直接结束,如果数据正确则判断是否为父节点
        if(categoryEntity.getIsParent() == 1) return this.setResultError("当前为父节点,不能被删除");

        Example example1 = new Example(CategoryBrandEntity.class);
        example1.createCriteria().andEqualTo("categoryId",id);
        List<CategoryBrandEntity> categoryBrandEntities = categoryBrandMapper.selectByExample(example1);
        if(categoryBrandEntities.size() == 1) return this.setResultError("当前分类已被品牌绑定,不能被删除");

        //通过被删除的父节点id,查询数据
        Example example = new Example(CategoryEntity.class);
        example.createCriteria().andEqualTo("parentId",categoryEntity.getParentId());
        List<CategoryEntity> entityList = categoryMapper.selectByExample(example);


        //判断父节点的个数是否<=1,如果小于等于1的话为true 就把父节点的状态修改为叶子节点
        //如果是false的话  直接删除点前节点
        if(entityList.size() <= 1){

            CategoryEntity updateEntity = new CategoryEntity();
            updateEntity.setIsParent(0);
            updateEntity.setId(categoryEntity.getParentId());

            categoryMapper.updateByPrimaryKeySelective(updateEntity);
        }
        categoryMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess("删除成功");
    }
}
