package com.pegasus.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DBWriteToExcel<T> {
	/**
	 * 根据数据返回Workbook
	 * @param dataList 数据
	 * @param sheetName 表格名称
	 * @param clazz bean类型 
	 * @return 工作薄
	 */
	public static <T> XSSFWorkbook writer(List<T> dataList,String sheetName,Class<T> clazz) {
		
		//1.新建excel报表
		XSSFWorkbook workbook=new XSSFWorkbook();
		//2添加一个sheet,名字叫"sheetName;
		XSSFSheet sheet=workbook.createSheet(sheetName);
		//3往excel表中创建表头！,excel的行号从0开始的；
		XSSFRow row=sheet.createRow(0);
		
		Field[] fields=clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++) {
			Field field=fields[i];
			String fieldName=field.getName();
			XSSFCell cell=row.createCell(i);
			cell.setCellValue(fieldName);
		}

		//0以下生产单元格内容；
		try {
			for(int i=0;i<dataList.size();i++) {
				
				T obj=dataList.get(i);
				//1创建 一行,前面已建立了表头占用一行，所用i+1；
				row=sheet.createRow(i+1);
				//2创建单元格(创建几个单元格？根据属性名利用反射来确定确性的值！）
				for(int j=0;j<fields.length;j++) {
					
					//1属性名；
					String fieldName=fields[j].getName();
					//2属性名对应的get方法名(注意getMethod有可能是is开头的boolean类型)；
					String getMethodName="get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					//2.注意getMethod有可能是is开头的boolean类型
					String getIsMethodName="is"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
					//3get方法；
					Method getMethod=null;
					try {
						getMethod=clazz.getDeclaredMethod(getMethodName);
					}catch(Exception e) {
						//System.out.println("There is ISxxx method in GETTINGs.");
						getMethod=clazz.getDeclaredMethod(getIsMethodName);
					}
					//4方法调用；
					Object value=getMethod.invoke(obj);
					//5创建单元格
					XSSFCell cell=row.createCell(j);
					//6给单元格赋值；
					//cell.setCellValue(value.toString());
					cell.setCellValue(value==null?"-":value.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return workbook;
	}	
}
