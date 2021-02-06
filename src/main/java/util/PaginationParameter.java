package util;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author:      yzc
 * Description: 分页参数类
 */

/**
 * 分页参数类
 */
@ApiModel(value = "分页参数类")
public class PaginationParameter extends BaseObject {

    /**
     * 单页数据量
     */
    @ApiModelProperty(value = "单页数据量",example = "10")
    private Integer pageSize;

    /**
     * 当前数据页码
     */
    @ApiModelProperty(value = "当前数据页码",example = "1")
    private Integer pageNum;

    /**
     * 目前不考虑字段排序,默认为null
     */
    private String pageOrder;

    /**
     * 数据总量
     */
    private Long pageDataSize;

    /*
    * token
    * */
    private String token;

    public Integer getPageSize() {
        return pageSize == null || pageSize < 1 ? (pageSize = 10) : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Integer getPageNum() {
        return pageNum == null || pageNum < 1 ? (pageNum = 1)
                : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(String pageOrder) {
        this.pageOrder = pageOrder;
    }

    public Long getPageDataSize() {
        return pageDataSize;
    }

    public void setPageDataSize(Long pageDataSize) {
        if (pageDataSize == null || pageDataSize < 1) {
            this.pageDataSize = 0l;
        } else {
            this.pageDataSize = pageDataSize;
        }
    }

    public Integer getPageDataStart() {
        return (getPageNum() - 1) * getPageSize();
    }

    public Integer getPageDataEnd() {
        return getPageDataStart() + getPageSize() - 1;
    }

    public Map<String, String> getPageOrders() {
        return getOrders(pageOrder);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private static Map<String, String> getOrders(String pageOrder) {
        Map<String, String> orderMap = null;
        if (StringUtils.isNotBlank(pageOrder)) {
            String[] split = pageOrder.split(";");
            orderMap = new LinkedHashMap<>(split.length);
            for (String orderStr : split) {
                String[] split1 = orderStr.split(":");
                orderMap.put(split1[0], split1.length > 1 ? split1[1] : "0");
            }
        }
        return orderMap;
    }

}
