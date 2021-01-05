package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "规格接口")
public interface SpecificationService {

    @ApiOperation(value="通过调价查询规格组")
    @GetMapping(value="spec/list")
    Result<List<SpecGroupEntity>> getSepcgroupInfo(SpecGroupDTO specGroupDTO);

    @ApiOperation(value="新增规格组")
    @PostMapping(value="spec/save")
    Result<JSONObject> saveSecpgroup(@RequestBody SpecGroupDTO specGroupDTO);

    @ApiOperation(value="修改规格组")
    @PutMapping(value="spec/save")
    Result<JSONObject> editSecpgroup(@RequestBody SpecGroupDTO specGroupDTO);

    @ApiOperation(value = "删除规格组")
    @DeleteMapping(value = "spec/delete/{id}")
    Result<JSONObject> deleteSpecGroupById(@PathVariable Integer id);

    @ApiOperation(value="通过调价查询规格的参数")
    @GetMapping(value="spec/Paramlist")
    Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDTO);

    @ApiOperation(value="新增规格的参数")
    @PostMapping(value="param/save")
    Result<JSONObject> saveParam(@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value="修改规格的参数")
    @PutMapping(value="param/save")
    Result<JSONObject> editParam(@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value="删除规格参数")
    @DeleteMapping(value="param/delete")
    Result<JSONObject> deleteParam(Integer id);

}
