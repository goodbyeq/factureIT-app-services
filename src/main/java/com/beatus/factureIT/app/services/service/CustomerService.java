package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.model.Customer;
import com.beatus.factureIT.app.services.repository.CustomerRepository;

@Service
@Component("customerService")
public class CustomerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	@Resource(name = "customerRepository")
	private CustomerRepository customerRepository;

	public Customer getCustomerByCustomerId(String id) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getCustomerByCustomerId");
		Customer customer = customerRepository.getCustomerByCustomerId(id);
		return customer;
	}
	
	public Customer getCustomerByUID(String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getCustomerByUID");
		Customer customer = customerRepository.getCustomerByUID(uid);
		return customer;
	}

	public List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllCustomers");
		List<Customer> customers = customerRepository.getAllCustomers();
		return customers;
	}

}