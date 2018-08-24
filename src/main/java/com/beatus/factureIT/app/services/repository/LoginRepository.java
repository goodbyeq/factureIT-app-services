package com.beatus.factureIT.app.services.repository;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import main.java.com.beatus.factureIT.app.services.model.CollectionAgent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.mapper.UserMapper;
import com.beatus.factureIT.app.services.repository.LoginRepository;
import com.beatus.factureIT.app.services.repository.ManufacturerRepository;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.Utils;

@Component("loginRepository")
public class LoginRepository {
	
	JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginRepository.class);

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Resource(name = "distributorRepository")
	DistributorRepository distributorRepository;

	@Resource(name = "retailerRepository")
	RetailerRepository retailerRepository;

	@Resource(name = "customerRepository")
	CustomerRepository customerRepository;

	@Resource(name = "manufacturerRepository")
	ManufacturerRepository manufacturerRepository;
	
	@Resource(name = "collectionAgentRepository")
	CollectionAgentRepository collectionAgentRepository;
	
	@Autowired
	public LoginRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addUser(User user) throws ClassNotFoundException, SQLException {
		if (user.getUserType() != null && user.getUserType().size() > 0) {
			try {
				LOGGER.info("In adduser");
				String sql = "INSERT INTO users (username, password, uid, user_type, isVerified) VALUES (?, ?, ?, ?, ?)";
				int rowsInserted = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getUid(),
						user.getUserType().toString(), user.getIsVerified());

				if (rowsInserted > 0) {
					LOGGER.info("A new user was inserted successfully!");
					for (String type : user.getUserType()) {
						if (Constants.DISTRIBUTOR_TYPE.equals(type)) {
							distributorRepository.addDistributor(user);
						}
						if (Constants.RETAILER_TYPE.equals(type)) {
							retailerRepository.addRetailer(user);
						}
						if (Constants.CUSTOMER_TYPE.equals(type)) {
							customerRepository.addCustomer(user);
						}
						if (Constants.MANUFACTURER_TYPE.equals(type)) {
							manufacturerRepository.addManufacturer(user);
						}
					}
					return Constants.USER_CREATED;
				} else {
					return Constants.ERROR_USER_CREATION;
				}
			} finally {
				
			}
		}
		return Constants.ERROR_USER_CREATION;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addCollectionAgent(CollectionAgent agent,String uid) throws ClassNotFoundException, SQLException{
		try {
			LOGGER.info("In addCollectionAgent");
			agent.setUid(Utils.generateRandomKey(50));
			String response = addUser(agent);
			String response = collectionAgentRepository.addCollectionAgent(agent, uid);
			LOGGER.info("In addCollectionAgent response :" + response);
			return response;
		} finally {
		}
	}

	public User getUserByUsername(String username) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getUserByUsername " + username);
		String sql = "SELECT * FROM users WHERE username = ?";
		User user = jdbcTemplate.queryForObject(sql, new Object[] { username }, new UserMapper());
		LOGGER.info("The user returned " + user.getUsername());
		return user;
	}
}
