package util;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Author: yzc
 * @Description: ${description}
 * @Date: 2019/12/9 17:16
 * @Version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseObject implements Serializable {

    public String toString(){
        return JsonUtils.toJson(this);
    }
}
