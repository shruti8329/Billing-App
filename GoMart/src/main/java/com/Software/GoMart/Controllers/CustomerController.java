package com.Software.GoMart.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Software.GoMart.Entites.CustomerMaster;

import com.Software.GoMart.Services.CustomerService;

@RestController
@RequestMapping("/GoMart/customer")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping
	public List<CustomerMaster> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<CustomerMaster> addCustomer(@RequestBody CustomerMaster customer ){
//		System.out.println("In cont "+customer.getFullname()+" "+customer.getMobile());
		 CustomerMaster newCustomer = customerService.addCustomer(customer);
		 System.err.println("===== New User Added ===== ");
	     return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
		
	}

}
