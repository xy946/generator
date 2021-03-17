package main;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : yzc
 * @date : 2019/12/24 11:40
 **/
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    //todo 实体model添加信息
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //todo 实体添加import信息
//        topLevelClass.addImportedType("lombok.AllArgsConstructor");
//        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("com.fasterxml.jackson.annotation.JsonFormat");
        topLevelClass.addImportedType("javax.validation.constraints.NotBlank");
        topLevelClass.addImportedType("javax.validation.constraints.NotNull");
        //topLevelClass.addImportedType("org.apache.solr.client.solrj.beans.Field");  //solr注解导包


        //todo 实体添加注解信息
//        topLevelClass.addAnnotation("@AllArgsConstructor");
//        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@Data");

        //todo 实体添加作者时间
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("/* 描述:");
        topLevelClass.addJavaDocLine(" * @author : yzc ");
        topLevelClass.addJavaDocLine(" * @create : "+ date2Str(new Date()));
        topLevelClass.addJavaDocLine(" **/");

        return true;
    }

    //todo mapper 添加注解信息 import信息
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        //todo mapper添加import  和注解
        FullyQualifiedJavaType importedType1=new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper");
        FullyQualifiedJavaType importedType2=new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param");
        Set set=new HashSet();
        set.add(importedType1); set.add(importedType2);
        interfaze.addImportedTypes(set);
        interfaze.addAnnotation("@Mapper");

        //todo Mapper添加作者时间
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("/* 描述:");
        interfaze.addJavaDocLine(" * @author : yzc ");
        interfaze.addJavaDocLine(" * @create : "+ date2Str(new Date()));
        interfaze.addJavaDocLine(" **/");

        return true;
    }

    //todo 控制是否生成get方法   false不生成  true生成
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    //todo 控制是否生成set方法   false不生成  true生成
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
