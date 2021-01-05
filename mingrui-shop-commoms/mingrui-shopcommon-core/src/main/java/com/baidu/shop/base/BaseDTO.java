package com.baidu.shop.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
@ApiModel(value="Base用于传输数据,其它的dto需要继承此类")
public class BaseDTO {

    @ApiModelProperty(value = "当前的页数", example = "1")
    private Integer page;

    @ApiModelProperty(value="每页多少显示多少条数据",example = "5")
    private Integer rows;

    @ApiModelProperty(value="排序的字段")
    private String sort;

    @ApiModelProperty(value="是否排序")
    private String order;

    @ApiModelProperty(hidden = true)
    public String getOrderByClause(){
        if(!StringUtils.isEmpty(sort))return sort + " " +
                order.replace("false","asc").replace("true","desc");
        return "";
    }

}
