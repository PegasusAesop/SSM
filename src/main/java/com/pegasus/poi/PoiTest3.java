package com.pegasus.poi;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PoiTest3 {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("我的POI之旅");
		
		XSSFRow row0 = sheet.createRow(0);
		
		XSSFCell cell0 = row0.createCell(0);
		cell0.setCellValue("no");
		
		XSSFCell cell1 = row0.createCell(1);
		cell1.setCellValue("name");
		
		XSSFCell cell2 = row0.createCell(2);
		cell2.setCellValue("creDate");

		FileOutputStream fout=null;
		try {
			fout = new FileOutputStream("H:/customer.xlsx");
			workbook.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}
