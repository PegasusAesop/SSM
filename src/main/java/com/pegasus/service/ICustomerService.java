package com.pegasus.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.pegasus.domain.Customer;

public interface ICustomerService {

	PageInfo<Customer> findCustomerByPage(int pageNum,int pageSize);

	Customer selectById(String id);

	List<Customer> findByIds(String[] ids);

	boolean batchImport(String fileName, MultipartFile file) throws Exception;
}
