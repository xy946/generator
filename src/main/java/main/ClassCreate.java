package main;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import util.Constants;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author : yzc
 * @date : 2020/8/27 15:07
 **/
public class ClassCreate {

    public static void generate(String path,String functionName, String ClassName,
                                String tableName,
                                String className,List<String> request,
                                List<String> response,List<String> mapperStr,
                                List<String> mapperCol,List<String> mapperIns,
                                List<String> mapperInsV,List<String> mapperUp) throws Exception {
        Properties p = new Properties();
        // 加载classpath目录下的vm文件
        p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 定义字符集
        p.setProperty(Velocity.ENCODING_DEFAULT, Constants.UTF8);
        p.setProperty(Velocity.OUTPUT_ENCODING, Constants.UTF8);
        // 初始化Velocity引擎，指定配置Properties
        Velocity.init(p);
        VelocityContext context = new VelocityContext();
        context.put("functionName", functionName);
        context.put("ClassName", ClassName);
        context.put("tableName", tableName);
        context.put("className",className);
        context.put("request",request);
        context.put("response",response);
        context.put("date",new SimpleDateFormat(Constants.YYYY_MM_DD).format(new Date()));
        context.put("mapperStr",mapperStr);
        context.put("mapperCol",mapperCol);
        context.put("mapperIns",mapperIns);
        context.put("mapperInsV",mapperInsV);
        context.put("mapperUp",mapperUp);
        List<String> templateList = getTemplateList();
        int i=0;
        for (String template :templateList){
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            OutputStream out = null;
            if(i==0){
                out = new FileOutputStream(path+ClassName+"Controller.java");
            }else if(i==1){
                out = new FileOutputStream(path+ClassName+"Service.java");
            }else if(i==2){
                out = new FileOutputStream(path+ClassName+"ServiceImpl.java");
            }else if(i==3){
                out = new FileOutputStream(path+ClassName+"Mapper.java");
            }else if(i==4){
                out = new FileOutputStream(path+ClassName+"Mapper.xml");
            }

            Writer writer = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));//解决乱码问题
            // 转换输出
            tpl.merge(context, writer);
            //System.out.println(writer.toString());
            writer.flush();
            out.close();
            i++;
        }
    }


    public static List<String> getTemplateList() {
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/mapper.xml.vm");
        return templates;
    }

}
