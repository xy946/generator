package util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Author:      yzc
 * Description: 分页结果类
 */

/**
 * 分页结果类
 */
@ApiModel(value = "分页结果类")
public class PaginationResponse<T> extends PaginationParameter {

    /**
     * 总共页数
     */
    @ApiModelProperty(value = "总共页数")
    private Integer total;

    /**
     * 当前页数据
     */
    @ApiModelProperty(value = "当前页数据")
    private List<T> pageDataList;

    public List<T> getPageDataList() {
        return pageDataList;
    }

    public void setPageDataList(List<T> pageDataList) {
        this.pageDataList = pageDataList;
    }

    public static <T> PaginationResponse<T> getPagination(PaginationParameter pagination,
                                                          List<T> data) {
        PaginationResponse<T> response = new PaginationResponse<>();
        response.setPageDataList(data);
        response.setPageOrder(null);
        response.setPageNum(null);
        response.setPageSize(null);
        return response;
    }

    public static <T> PaginationResponse<T> getPagination(PaginationParameter pagination, Long size,
                                                          List<T> data) {
        PaginationResponse<T> response = new PaginationResponse<>();
        response.setPageDataSize(size);
        response.setPageDataList(data);
        response.setPageOrder(pagination.getPageOrder());
        response.setPageNum(pagination.getPageNum());
        response.setPageSize(pagination.getPageSize());
        return response;
    }

    public static <T> PaginationResponse<T> getPagination(PaginationParameter pagination, Long size,
                                                          List<T> data,Integer pages) {
        PaginationResponse<T> response = new PaginationResponse<>();
        response.setPageDataSize(size);
        response.setPageDataList(data);
        response.setPageOrder(pagination.getPageOrder());
        response.setPageNum(pagination.getPageNum());
        response.setPageSize(pagination.getPageSize());
        response.setTotal(pages);
        return response;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
