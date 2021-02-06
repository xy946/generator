package main;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author : yzc
 * @ Date   : 2019/5/23 16:39
 **/
public class Generator {

    public static void main(String[] args) throws Exception {
        String functionName="部门";   //类描述
        String ClassName="Department";     //类名
        String className="department";     //类名驼峰
        String tableName = "department";   //表名
        String path="C:\\Users\\Lenovo\\IdeaProjects\\generator\\src\\main\\java\\classfile\\";
        //MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<String>();
        //当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        //读取我们的 MBG 配置文件
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //创建 MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        //执行生成代码
        myBatisGenerator.generate(null);
        //获取保存的注释信息
        List<String> request = CommentGenerator.request;
        List<String> response = CommentGenerator.response;
        List<String> mapperStr = CommentGenerator.mapperStr;
        List<String> mapperCol = CommentGenerator.mapperCol;
        List<String> mapperIns = CommentGenerator.mapperIns;
        List<String> mapperInsV = CommentGenerator.mapperInsV;
        List<String> mapperUp = CommentGenerator.mapperUp;
        ClassCreate.generate(path,functionName,ClassName,tableName,className,
                request,response,mapperStr,mapperCol,mapperIns,mapperInsV,mapperUp);
        //输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
