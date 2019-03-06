package com.pegasus.poi;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pegasus.domain.Customer;


public class PoiTest6 {

	public static void main(String[] args) throws Exception {
		
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
		
		Customer cr4=new Customer();
		cr3.setId("0004");
		cr3.setName("胡一刀");
		cr3.setCreDate(LocalDate.parse("2013-02-11"));
		
		List<Customer> customers=new ArrayList<>();
		customers.add(cr1);
		customers.add(cr2);
		customers.add(cr3);
		customers.add(cr4);
		
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("客户信息表");
		
		XSSFRow row0 = sheet.createRow(0);
		
		Class<Customer> customerClass=Customer.class;
		Field[] fields = customerClass.getDeclaredFields();
		
		for(int i=0;i<fields.length;i++) {
			
			Field field = fields[i];
			XSSFCell cell = row0.createCell(i);
			cell.setCellValue(field.getName());
		}
		
		
		
		for(int i=0;i<customers.size();i++) {
			
			XSSFRow row = sheet.createRow(i+1);
			Customer customer = customers.get(i);
			
			for(int j=0;j<fields.length;j++) {
				
				Field field = fields[j];
				String fieldName=field.getName();
				//1对应属性名的get方法；
				String getMethodName="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				Method getMethod = customerClass.getDeclaredMethod(getMethodName);
				Object value = getMethod.invoke(customer);
				//2创建单元格；
				XSSFCell cell=row.createCell(j);
				//3给单元格赋值；
				cell.setCellValue(value==null?"":value.toString());
			}
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
