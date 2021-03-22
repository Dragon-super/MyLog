package cn.zglong.mylog.common.util.excel;

import org.apache.poi.hssf.usermodel.*;

public class ExcelUtil {
    public static HSSFWorkbook getHSSFWorkbook(String shettName, String[] title, String[][] value, HSSFWorkbook wb){
        //1.创建HSSFWorkbook 对象,对应一个Excel文件
        if (wb == null){
            wb = new HSSFWorkbook();
        }
        //2.在表格中添加shett,对应Excel的shett
        HSSFSheet sheet = wb.createSheet(shettName);
        //3.在shett中添加表头,对应第0行
        HSSFRow row = sheet.createRow(0);
        //4.创建单元格,设置表头值
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        //5.声明列对象
        HSSFCell cell  = null;
        //6.创建内容
        for(int i= 0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //7.创建内容
        for(int i= 0;i<value.length;i++){
            row = sheet.createRow(i+1);
            for(int k = 0; k<value[i].length;k++){
                row.createCell(k).setCellValue(value[i][k]);
            }
        }
        return wb;

    }
}
