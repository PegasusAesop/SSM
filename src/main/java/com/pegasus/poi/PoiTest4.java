package com.pegasus.poi;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pegasus.domain.Customer;


public class PoiTest4 {

	public static void main(String[] args) {
		
		Customer cr1=new Customer();
		cr1.setId("0001");
		cr1.setName("胡兴胜");
		cr1.setCreDate(LocalDate.parse("2019-09-09"));
		
		Customer cr2=new Customer();
		cr2.setId("0002");
		cr2.setName("刘艳芝");
		cr2.setCreDate(LocalDate.parse("2018-11-08"));
		
		Customer cr3=new Customer();
		cr3.setId("0003");
		cr3.setName("胡伊曼");
		cr3.setCreDate(LocalDate.parse("2017-09-09"));
		
		List<Customer> customers=new ArrayList<>();
		customers.add(cr1);
		customers.add(cr2);
		customers.add(cr3);
		
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("客户信息表");
		
		XSSFRow row0 = sheet.createRow(0);
		
		XSSFCell cell0 = row0.createCell(0);
		cell0.setCellValue("no");
		
		XSSFCell cell1 = row0.createCell(1);
		cell1.setCellValue("name");
		
		XSSFCell cell2 = row0.createCell(2);
		cell2.setCellValue("creDate");
		
		for(int i=0;i<customers.size();i++) {
			
			XSSFRow row = sheet.createRow(i+1);
			Customer customer = customers.get(i);
			XSSFCell c0 = row.createCell(0);
			c0.setCellValue(customer.getId());
			
			XSSFCell c1 = row.createCell(1);
			c1.setCellValue(customer.getName());
			
			XSSFCell c2 = row.createCell(2);
			c2.setCellValue(customer.getCreDate().toString());
		}
		
		
		

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
