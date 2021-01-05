package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "品牌的接口")
public interface BrandService {

    @GetMapping(value="brand/list")
    @ApiOperation(value="获取品牌的信息")
    Result<PageInfo<BrandEntity>> getBrandInfo(BrandDTO brandDTO);

    @PostMapping(value="brand/save")
    @ApiModelProperty(value="品牌新增")
    Result<JSONObject> saveBrandInfo(@RequestBody BrandDTO brandDTO);

    @PutMapping(value="brand/save")
    @ApiModelProperty(value="查询品牌列表")
    Result<JSONObject> editBrandInfo(@RequestBody BrandDTO brandDTO);

    @DeleteMapping(value="brand/dels")
    @ApiModelProperty("删除该品牌")
    Result<JSONObject> deleteBrandById(Integer id);

}
