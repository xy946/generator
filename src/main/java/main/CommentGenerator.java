package main;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ Author : yzc
 * @ Date   : 2019/5/23 17:36
 **/
public class CommentGenerator extends DefaultCommentGenerator {

    public static List<String> request = new ArrayList<>();

    public static List<String> response = new ArrayList<>();

    public static List<String> mapperStr = new ArrayList<>();
    public static List<String> mapperCol = new ArrayList<>();
    public static List<String> mapperIns = new ArrayList<>();
    public static List<String> mapperInsV = new ArrayList<>();
    public static List<String> mapperUp = new ArrayList<>();

    private boolean addRemarkComments = false;
    private static final String EXAMPLE_SUFFIX = "Example";
    private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME = "io.swagger.annotations.ApiModelProperty";
    private int i = 0;

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /**
     * 给字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        String remarks = introspectedColumn.getRemarks();               //sql字段释义
        String jdbcTypeName = introspectedColumn.getJdbcTypeName();     //sql字段类型
        String colName = introspectedColumn.getActualColumnName();      //sql字段名
        String name = field.getName();                                  //实体类名
        String shortName = field.getType().getShortName();              //实体类类型

        String re = "@apiParam     {" + shortName + "}      " + name + "       " + remarks;
        String rs = "@apiSuccess     {" + shortName + "}      " + name + "       " + remarks;
        String rsMap = "<result column=\""+colName+"\" jdbcType=\""+jdbcTypeName+"\" property=\""+name+"\" />";
        String ins = "<if test=\""+name+" != null\">\n";
        ins += colName+",\n";
        ins += "</if>\n";
        String insV = "<if test=\""+name+" != null\">\n";
        insV += name+",\n";
        insV += "</if>\n";
        String up = "<if test=\""+name+" != null\">\n";
        up += colName+" = #{"+name+",jdbcType=VARCHAR},\n";
        up += "</if>\n";
        request.add(re);
        response.add(rs);
        mapperStr.add(rsMap);
        mapperCol.add(colName);
        mapperIns.add(ins);
        mapperInsV.add(insV);
        mapperUp.add(up);

        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            addFieldJavaDoc(field, remarks);
            //数据库中特殊字符需要转义
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"", "'");
            }

            //todo 给model的字段添加swagger注解 swagger二选一  格式例: @ApiModelProperty(value = "确认收货时间" ,example = "")
            //Object example = setExample(jdbcTypeName, remarks);
            //field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" ,example = \"" + example + "\")");
            field.addJavaDocLine("/**");
            field.addJavaDocLine( "*" + remarks);
            field.addJavaDocLine( "*/");

            //todo 给model的字段添加swagger注解 swagger二选一  格式例: @ApiModelProperty(value = "确认收货时间")
            //field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" )");

            //todo solr注解
//            field.addJavaDocLine("@Field( \""+ field.getName() + "\")");

            //todo 为date类型字段自动添加@JsonFormat 注解
            if (jdbcTypeName.equals("TIMESTAMP") || jdbcTypeName.equals("TIME")) {
                field.addJavaDocLine("@JsonFormat(locale=\"zh\", timezone=\"GMT+8\", pattern=\"yyyy-MM-dd HH:mm:ss\")");
            }
            //todo 为字段添加校验
//            if(jdbcTypeName.equals("VARCHAR")||jdbcTypeName.equals("LONGVARCHAR")){
//                if(remarks.contains("时间")){
//
//                }else {
//                    field.addJavaDocLine("@NotBlank(message=\""+remarks+"不能为空\")");
//                }
//            }
//            if(jdbcTypeName.equals("INTEGER")||jdbcTypeName.equals("BIGINT")||jdbcTypeName.equals("DECIMAL")){
//                field.addJavaDocLine("@NotNull(message=\""+remarks+"不能为空\")");
//            }
        }
        super.addFieldComment(field, introspectedTable, introspectedColumn);
    }

    /**
     * todo 给model的字段添加注释 例://订单id 注释格式二选一
     */
    private void addFieldJavaDoc(Field field, String remarks) {
        //todo 给model的字段添加注释 例://订单id
//        String[] remarkLines=remarks.split(System.getProperty("line.separator"));//换行
//        field.addJavaDocLine("//"+remarkLines[0]);
        //todo 给model的字段添加注释 例: /**
        //todo                           *  订单id
        //todo                           */
//        field.addJavaDocLine("/**");
//        String[] remarkLines=remarks.split(System.getProperty("line.separator"));//换行
//        field.addJavaDocLine(" * "+remarkLines[0]);
//        field.addJavaDocLine(" */");
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);
        if (compilationUnit.isJavaInterface() && compilationUnit.
                getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)) {
            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
        }
    }


    private Object setExample(String jdbcTypeName, String remarks) {

        switch (jdbcTypeName) {
            case "BIGINT":
                return 2L;
            case "VARCHAR":
                if (remarks.contains("时间")) {
                    return "2018-08-08 20:00:00";
                } else {
                    return remarks;
                }
            case "LONGVARCHAR":
                return remarks;
            case "TIMESTAMP":
                return "2018-08-08 20:00:00";
            case "TIME":
                return "2018-08-08 20:00:00";
            case "DECIMAL":
                return "6.66";
            case "INTEGER":
                return "1";
        }
        return null;
    }

}
