package com.beatus.factureIT.app.services.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.factureIT.app.services.model.Customer;
import com.beatus.factureIT.app.services.model.CustomerResponse;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.UserCreatedResponse;
import com.beatus.factureIT.app.services.service.CustomerService;
import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_CUSTOMER_REQUEST)
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Resource(name = "customerService")
	private CustomerService customerService;

	public static JSendResponse<CustomerResponse> jsend(CustomerResponse customerResponse) {
		if (customerResponse == null || customerResponse.getCustomers() == null
				|| customerResponse.getCustomers().get(0) == null) {
			return new JSendResponse<CustomerResponse>(Constants.FAILURE, customerResponse);
		} else {
			return new JSendResponse<CustomerResponse>(Constants.SUCCESS, customerResponse);
		}
	}
	
	public static JSendResponse<Customer> jsend(Customer customer) {
		if (customer == null || customer == null) {
			return new JSendResponse<Customer>(Constants.FAILURE, customer);
		} else {
			return new JSendResponse<Customer>(Constants.SUCCESS, customer);
		}
	}

	@RequestMapping(value = Constants.WEB_CUSTOMER_GET_CUSTOMER_BY_ID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Customer> getCustomerByIdGet(HttpServletRequest request,
			ModelMap model, @RequestParam String customerId) throws ClassNotFoundException, SQLException {
		Customer customer = customerService.getCustomerByCustomerId(customerId);
		LOGGER.info("After the get call and the customer is " + customer != null
				? customer.getFirstname() : "No Customer data");
		return jsend(customer);
	}

	@RequestMapping(value = Constants.WEB_CUSTOMER_GET_CUSTOMER_BY_UID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Customer> getCustomerByUIDGet(HttpServletRequest request,
			ModelMap model, @RequestParam String uid) throws ClassNotFoundException, SQLException {
		Customer customer = customerService.getCustomerByUID(uid);
		LOGGER.info("After the get call and the customer is " + customer != null
				? customer.getFirstname() : "No Customer data");
		return jsend(customer);
	}

	@RequestMapping(value = Constants.WEB_CUSTOMER_GET_ALL_CUSTOMERS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<CustomerResponse> getAllCustomersGet(HttpServletRequest request,
			ModelMap model) throws ClassNotFoundException, SQLException {
		List<Customer> customers = customerService.getAllCustomers();
		LOGGER.info("After the get call and the customers are " + customers != null
				? customers.size() > 0 ? customers.get(0).getFirstname() : "No Customer data"
				: "No Customer data");
		CustomerResponse resp = new CustomerResponse();
		resp.setCustomers(customers);
		return jsend(resp);
	}
}
