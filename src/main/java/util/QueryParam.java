package util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : yzc
 * @date : 2020/7/6 9:46
 **/
@ApiModel(value = "查询参数类")
@Data
public class QueryParam extends PaginationParameter {

    @ApiModelProperty(value = "标题" ,example = "我是标题")
    private String title;

    @ApiModelProperty(value = "类型" ,example = "我是类型")
    private String type;

}
