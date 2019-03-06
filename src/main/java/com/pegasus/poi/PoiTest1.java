package com.pegasus.poi;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class PoiTest1 {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		HSSFWorkbook excel = new HSSFWorkbook();
		HSSFSheet hssfSheet = excel.createSheet("我的POI之旅");
		
		HSSFRow hssfRow = hssfSheet.createRow(0);
		HSSFCell hssfCell = hssfRow.createCell(0);
		
		hssfCell.setCellValue("poi");

		FileOutputStream fout=null;
		try {
			fout = new FileOutputStream("H:/customer.xls");
			excel.write(fout);
			fout.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}
