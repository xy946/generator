package util;

/**
 * Date:        2019/12/10
 * Author:      yzc
 */
public enum StatusCode implements GlobalStatusCode {

    RESULT_SUCCESS("200", "SUCCESS"),
    DATA_ERROR("500" , "数据错误"),
    QUERY_PARAM_ERROR("002" , "参数错误"),
    UPLOAD_FILE_ERROR("003" , "上传文件失败"),
    REPEAT_ERROR("004" , "用户已存在"),
    REPETITION_ERROR("005" , "类型和名字已存在"),
    TOKEN_ERROR("006" , "token异常"),
    REDIS_ERROR("007" , "redis异常"),
    OPERA_DATA_NULL("008","操作数据不存在"),
    DATA_NULL("009","查询数据为空"),
    SYSTEM_EXCEPTION("999", "系统异常");
    private String statusCode;
    private String message;

    StatusCode(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
