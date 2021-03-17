package util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    //新增
    public final static String METHOD_ADD = "add";

    //查询单条详情
    public final static String METHOD_INFO = "info";

    //条件分页查询
    public final static String METHOD_LIST = "list";

    //修改
    public final static String METHOD_UPDATE = "update";

    //删除
    public final static String METHOD_DEL = "del";

    //参数错误状态码
    public final static String PARAM_ERROR = "99999999";

    //redis  缓存时间
    public  final static int  REDIS_TIME_OUT = 60*30;

    public final static String YYYY_MM_DD = "yyyy-MM-dd HH:mm:ss";

    /****************************************************************File相关********************************************************************************************************/
    //文件上传保存路径
    @Value("${file.path}")
    public String FILE_PATH ;

    //文件访问头地址
    @Value("${file.head.url}")
    public String FILE_HEAD_URL;

    /****************************************************************Ftp相关********************************************************************************************************/



    //账号
    @Value("${ftp.account}")
    public String FTP_ACCOUNT;

    //密码
    @Value("${ftp.password}")
    public String FTP_PASSWORD;

    //端口
    @Value("${ftp.port}")
    public int FTP_PORT;

    //ip地址
    @Value("${ftp.server}")
    public  String FTP_SERVERIP;


}
