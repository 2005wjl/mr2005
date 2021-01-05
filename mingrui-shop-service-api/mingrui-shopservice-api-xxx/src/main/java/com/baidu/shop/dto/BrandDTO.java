package com.baidu.shop.dto;

import com.baidu.shop.base.BaseDTO;
import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="品牌DTO")
public class BrandDTO extends BaseDTO {

    @ApiModelProperty(value="品牌id",example = "1")
    @NotNull(message="品牌id不能为空",groups = {MingruiOperation.Update.class})
    private Integer id;

    @ApiModelProperty(value="品牌名称")
    @NotEmpty(message = "品牌名称不能为空",groups = {MingruiOperation.Add.class,MingruiOperation.Update.class})
    private String  name;

    @ApiModelProperty(value="品牌图片地址不能为空")
    private String image;

    @ApiModelProperty(value="品牌首字母")
    private Character letter;

    @ApiModelProperty(value="品牌id的集合")
    private String categories;

}
