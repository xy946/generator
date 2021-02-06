package test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author : yzc
 * @date : 2020/8/25 15:23
 **/
public class FileToExcel {
    //创建表头
    public static void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //列宽，第一个参数为第几列，第二个参数是宽度，基本单位为1/256个字符宽度
        //要想得到准确的值，按下面方式直接写就可以
        sheet.setColumnWidth(0, (int)(20+0.72)*256);//实际宽度为20
        sheet.setColumnWidth(1, (int)(30+0.72)*256);//实际宽度为30

        //设置表头格式
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);//加粗
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setFont(font);

        //设置表头
        HSSFCell cell;
        cell = row.createCell(0);//标明第几列
        cell.setCellValue("文件中文名");//表头
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("文件名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("作者");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("简介");
        cell.setCellStyle(style);
    }

    //生成Excel文件
    public static void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        workbook.close();

    }

    //把想要的数据放到Excel表格中
    public static void main(String[] args) throws Exception{
        String path = "E:\\epub\\";
        File file = new File(path);
        //list()方法是返回某个目录下的所有文件和目录的文件名，返回的是String数组
        //listFiles()方法是返回某个目录下所有文件和目录的绝对路径，返回的是File数组
        File[] fs = file.listFiles();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("epub");
        createTitle(workbook,sheet);
        int rowNum = 1;
        for(File f : fs){
            //java中的isDirectory()是检查一个对象是否是文件夹。返回值是boolean类型的。如果是则返回true，否则返回false。
            if(!f.isDirectory()){
                String s = f.getName();
                //split():字符串分隔符，默认加“\\”,这里的意思就是文件名被“_”分开
                String[] strArr = s.split("\\_");
                HSSFRow row = sheet.createRow(rowNum);
                for(int i = 0;i<strArr.length;i++){
                    row.createCell(i).setCellValue(strArr[i]);
                }
                rowNum++;
            }
        }
        String fileName = "E:\\file\\名称.xls";
        //生成Excel文件
        buildExcelFile(fileName,workbook);
        System.out.println("生成完成");
    }
}
