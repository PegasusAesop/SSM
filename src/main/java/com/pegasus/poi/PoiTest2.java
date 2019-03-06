package com.pegasus.poi;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PoiTest2 {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		XSSFWorkbook excel = new XSSFWorkbook();
		XSSFSheet hssfSheet = excel.createSheet("我的POI之旅");
		
		XSSFRow hssfRow = hssfSheet.createRow(0);
		XSSFCell hssfCell = hssfRow.createCell(0);
		
		hssfCell.setCellValue("poi");

		FileOutputStream fout=null;
		try {
			fout = new FileOutputStream("H:/customer.xlsx");
			excel.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}
