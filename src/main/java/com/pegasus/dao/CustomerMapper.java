package com.pegasus.dao;

import java.util.List;

import com.pegasus.domain.Customer;

public interface CustomerMapper {

	int insertCustomer(Customer customer);
	int deleteCustomerById(String id);
	int batchDeleteCustomerById(String...ids);
	
	int updateCustomerById(Customer customer);
	
	List<Customer> findAll();
	Customer selectByName(String name);
	Customer selectById(String id);
	List<Customer> selectByIds(String[] ids);
}
