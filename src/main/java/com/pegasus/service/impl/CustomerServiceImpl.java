package com.pegasus.service.impl;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pegasus.dao.CustomerMapper;
import com.pegasus.domain.Customer;
import com.pegasus.service.ICustomerService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public PageInfo<Customer> findCustomerByPage(int pageNum, int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		List<Customer> customers = customerMapper.findAll();
		PageInfo<Customer> pageInfo=new PageInfo<>(customers);
		
		return pageInfo;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
	public Customer selectById(String id) {
		
		Customer customer = customerMapper.selectById(id);
		return customer;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
	public List<Customer> findByIds(String[] ids) {
		// TODO Auto-generated method stub
		List<Customer> customers = customerMapper.selectByIds(ids);
		return customers;
	}
	
    @SuppressWarnings({ "resource" })
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		
		boolean notNull = false;
        List<Customer> customerList = new ArrayList<>();
        
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
        	
            throw new RuntimeException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        Customer customer;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
 
            customer = new Customer();
/*            
            if( row.getCell(0).getCellType() !=1){
            	
                throw new RuntimeException("导入失败(第"+(r+1)+"行,序号请设为文本格式)");
            }*/
            String id = row.getCell(0).getStringCellValue();
 
/*            if(id == null || id.isEmpty()){
                throw new RuntimeException("导入失败(第"+(r+1)+"行,序号未填写)");
            }
 
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);*/
            
            String name = row.getCell(1).getStringCellValue();
            /*if(name==null || name.isEmpty()){
                throw new RuntimeException("导入失败(第"+(r+1)+"行,姓名未填写)");
            }*/
             LocalDate creDate = LocalDate.parse(row.getCell(2).getStringCellValue());
            /*if(creDate==null){
                throw new RuntimeException("导入失败(第"+(r+1)+"行,不存在此单位或单位未填写)");
            }*/
 
            customer.setId(id);
            customer.setName(name);
            customer.setCreDate(creDate);
 
            customerList.add(customer);
        }
        
        for (Customer cust : customerList) {
        	
            String name = cust.getName();
            Customer c  = customerMapper.selectByName(name);
            if (c == null) {
                customerMapper.insertCustomer(cust);
                System.out.println(" 插入 "+cust);
            } else {
                customerMapper.updateCustomerById(cust);
                System.out.println(" 更新 "+cust);
            }
        }
        return notNull;
	}
	@Override
	public List<Customer> findCustomerAll() {
		List<Customer> customers = customerMapper.findAll();
		return customers;
	}
	
}
