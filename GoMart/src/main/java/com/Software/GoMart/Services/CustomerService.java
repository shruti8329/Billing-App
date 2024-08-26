package com.Software.GoMart.Services;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Software.GoMart.Entites.CustomerMaster;
import com.Software.GoMart.Repositiory.CustomerRepositiory;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepositiory customerRepo;
	
	public CustomerMaster addCustomer (CustomerMaster customer) {
		customer.setCreatedon(LocalDate.now());
		return customerRepo.save(customer);
		
	}
	// Get All Products
		public List<CustomerMaster> getAllCustomers() {
			return customerRepo.findAll();
		}
	
	
}
