package util;


/**
 * Author:     yzc
 * Description: 参数验证异常类
 */
public class GlobalException extends RuntimeException {
    
    private static final long serialVersionUID = -1178522679073184111L;
    
    private String statusCode;
    private String message;
    
    
    public GlobalException() {
        super();
    }

    public GlobalException(GlobalStatusCode status) {
        super();
        this.statusCode = status.getStatusCode();
        this.message = status.getMessage();
    }

    public GlobalException(GlobalStatusCode status, String... s) {
        super();
        this.statusCode = status.getStatusCode();
        this.message = status.getMessage();
        if(s!=null){
            for(String _s:s){
                this.message = this.message.replace("{}",_s);
            }
            //this.message = MessageFormatter.arrayFormat(message, s).getMessage();
        }
    }

    public GlobalException(GlobalStatusCode status, String s) {
        super();
        this.statusCode = status.getStatusCode();
        this.message = status.getMessage();
        if(s!=null&&s.equals("")){
            this.message=s;
        }
    }
    
    public <T> GlobalException(ResultVO<T> result) {
        this(result.getCode(),result.getMsg());
    }

    public GlobalException(String statusCode, String message, String... s) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        if(s!=null){
            for(String _s:s){
                this.message = this.message.replace("{}",_s);
            }
        }
    }

    public GlobalException(String message) {
        super();
        this.statusCode = "999";
        this.message = message;
    }

    public GlobalException(String statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

