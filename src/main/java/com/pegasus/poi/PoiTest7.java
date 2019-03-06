package com.pegasus.poi;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.pegasus.domain.Customer;
import com.pegasus.utils.DBWriteToExcel;

public class PoiTest7 {

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
		cr4.setId("0004");
		cr4.setName("胡一刀");
		cr4.setCreDate(LocalDate.parse("2013-02-11"));
		
		List<Customer> customers=new ArrayList<>();
		customers.add(cr1);
		customers.add(cr2);
		customers.add(cr3);
		customers.add(cr4);
		
		XSSFWorkbook workbook = DBWriteToExcel.writer(customers, "hello", Customer.class);
		
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
