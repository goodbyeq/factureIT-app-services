package com.beatus.factureIT.app.services.repository;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.Customer;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.mapper.CustomerMapper;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("customerRepository")
public class CustomerRepository {
	
private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRepository.class);
	
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public CustomerRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addCustomer(User customer) throws ClassNotFoundException, SQLException {

		try {
			LOGGER.info("In addCustomer");
			String id = Utils.generateRandomKey(50);
			String sql = "INSERT INTO customer (customer_id, uid, customer_first_name, customer_last_name, customer_phone, customer_email, customer_address, customer_city, customer_state, customer_zipcode, latitude, longitude ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(sql, id, Utils.generateRandomKey(50), customer.getUid(),
					customer.getCompanyName(), customer.getCompanyType(), customer.getFirstname(), customer.getLastname(), customer.getPhone(), customer.getEmail(), 
					customer.getAddress(), customer.getCity(), customer.getState(), customer.getZipcode(), customer.getLatitude(), customer.getLongitude());
		
			if (rowsInserted > 0) {
				LOGGER.info("A new customer was inserted successfully!");
				return id;
			} else {
				return null;
			}
		} finally {
		}
	}

	public boolean editCustomer(User customer) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In editCustomer");
			String sql = "UPDATE customer SET customer_company_name = ?, dictributor_company_type = ?, customer_first_name= ?, customer_last_name= ?, customer_phone= ?, customer_email= ?, customer_address= ?, customer_city= ?, customer_state = ?,  customer_zipcode= ? WHERE customer_id = ?";
			
			int rowsInserted = jdbcTemplate.update(sql, customer.getCompanyName(), customer.getCompanyType(), customer.getFirstname(),
					 customer.getLastname(), customer.getPhone(), customer.getEmail(), 
					customer.getAddress(), customer.getCity(), customer.getState(), customer.getZipcode(), customer.getId());
		
			if (rowsInserted > 0) {
				LOGGER.info("A customer was updated successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	public Customer getCustomerByCustomerId(String id) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getCustomerByCustomerId");
			String sql = "SELECT dist.customer_id AS customerId, dist.customer_company_name AS customerCompanyName, "
					+ " dist.customer_company_type AS customerCompanyType, dist.customer_company_id AS customerCompanyId, dist.uid AS uid, "
					+ " dist.customer_first_name AS customerFirstName, dist.customer_last_name AS customerLastName, "
					+ " dist.customer_phone AS customerPhone, dist.customer_email AS customerEmail, dist.customer_address AS customerAddress, "
					+ " dist.customer_city AS customerCity, dist.customer_state AS customerState, dist.customer_zipcode AS customerZipcode, "
					+ " u.username AS username, u.user_type AS userType, u.verified AS verified "
					+ " FROM customer dist, users u WHERE dist.uid = u.uid AND dist.customer_id = ? ";
			Customer customer = jdbcTemplate.queryForObject(sql, new Object[] {  id }, new CustomerMapper());
			return customer;
		} finally {
		}
	}

	public Customer getCustomerByUID(String uid) throws ClassNotFoundException, SQLException {
		
		try {
			LOGGER.info("In getCustomerByCustomerId");
			String sql = "SELECT dist.customer_id AS customerId, dist.customer_company_name AS customerCompanyName, "
					+ " dist.customer_company_type AS customerCompanyType, dist.customer_company_id AS customerCompanyId, dist.uid AS uid, "
					+ " dist.customer_first_name AS customerFirstName, dist.customer_last_name AS customerLastName, "
					+ " dist.customer_phone AS customerPhone, dist.customer_email AS customerEmail, dist.customer_address AS customerAddress, "
					+ " dist.customer_city AS customerCity, dist.customer_state AS customerState, dist.customer_zipcode AS customerZipcode, "
					+ " u.username AS username, u.user_type AS userType, u.verified AS verified "
					+ " FROM customer dist, users u WHERE dist.uid = u.uid AND u.uid = ? ";
			Customer customer = jdbcTemplate.queryForObject(sql, new Object[] {  uid }, new CustomerMapper());
			return customer;
		} finally {
		}
	}
	
	public List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getAllCustomers");
			String sql = "SELECT dist.customer_id AS customerId, dist.customer_company_name AS customerCompanyName, "
					+ " dist.customer_company_type AS customerCompanyType, dist.customer_company_id AS customerCompanyId, dist.uid AS uid, "
					+ " dist.customer_first_name AS customerFirstName, dist.customer_last_name AS customerLastName, "
					+ " dist.customer_phone AS customerPhone, dist.customer_email AS customerEmail, dist.customer_address AS customerAddress, "
					+ " dist.customer_city AS customerCity, dist.customer_state AS customerState, dist.customer_zipcode AS customerZipcode, "
					+ " u.username AS username, u.user_type AS userType, u.verified AS verified "
					+ " FROM customer dist, users u WHERE dist.uid = u.uid";
			List<Customer> customers = jdbcTemplate.query(sql, new CustomerMapper());
			return customers;
		} finally {
		}
	}

	public void deleteCustomer(int customerId, String companyId) throws SQLException {
		/*String sql = "DELETE FROM customer where customer_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, customerId);
		statement.setString(2, companyId);

		boolean result = statement.execute();
		return result;*/
	}
}
