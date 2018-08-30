package com.beatus.factureIT.app.services.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.CollectionAgent;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.UserDeviceInfo;
import com.beatus.factureIT.app.services.model.mapper.UserDeviceInfoMapper;
import com.beatus.factureIT.app.services.model.mapper.UserMapper;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

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
				String sql = "INSERT INTO users (username, password, uid, user_type, verified) VALUES (?, ?, ?, ?, ?)";
				int rowsInserted = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getUid(),
						user.getUserType().toString(), user.getIsVerified());

				if (rowsInserted > 0) {
					LOGGER.info("A new user was inserted successfully!");
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
	public String addUserDeviceInfo(UserDeviceInfo userDeviceInfo) throws ClassNotFoundException, SQLException {
		if (userDeviceInfo != null && userDeviceInfo.getDeviceId() != null) {
			try {
				LOGGER.info("In addUserDeviceInfo");
				String sql = "INSERT INTO users_device_info (users_device_info_id, device_id, firebase_id, gcm_id, vision_id, uid) VALUES (?, ?, ?, ?, ?, ?)";
				int rowsInserted = jdbcTemplate.update(sql, Utils.generateRandomKey(50), userDeviceInfo.getDeviceId(), userDeviceInfo.getFirebaseId(),
						userDeviceInfo.getGcmId(), userDeviceInfo.getVisionId(), userDeviceInfo.getUid());

				if (rowsInserted > 0) {
					LOGGER.info("A new user device info was inserted successfully!");
					return Constants.USER_DEVICE_INFO_CREATED;
				} else {
					return Constants.ERROR_USER_DEVICE_INFO_CREATION;
				}
			} finally {

			}
		}
		return Constants.ERROR_USER_DEVICE_INFO_CREATION;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String updateUserDeviceInfo(UserDeviceInfo userDeviceInfo) throws ClassNotFoundException, SQLException {
		if (userDeviceInfo != null && userDeviceInfo.getDeviceId() != null) {
			try {
				LOGGER.info("In addUserDeviceInfo");
				String sql = "UPDATE users_device_info SET device_id = ?, firebase_id = ?, gcm_id = ?, vision_id = ? WHERE uid = ?";
				int rowsUpdated = jdbcTemplate.update(sql, userDeviceInfo.getDeviceId(), userDeviceInfo.getFirebaseId(),
						userDeviceInfo.getGcmId(), userDeviceInfo.getVisionId(), userDeviceInfo.getUid());

				if (rowsUpdated > 0) {
					LOGGER.info("A new user device info was inserted successfully!");
					return Constants.USER_DEVICE_INFO_CREATED;
				} else {
					return Constants.ERROR_USER_DEVICE_INFO_CREATION;
				}
			} finally {

			}
		}
		return Constants.ERROR_USER_DEVICE_INFO_CREATION;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<UserDeviceInfo> getUserDeviceInfo(String uid) throws ClassNotFoundException, SQLException {
		if (uid != null) {
			try {
				LOGGER.info("In getUserDeviceInfo " + uid);
				String sql = "SELECT * FROM users_device_info WHERE uid = ?";
				List<UserDeviceInfo> usersDeviceInfo = jdbcTemplate.query(sql, new Object[] { uid }, new UserDeviceInfoMapper());
				LOGGER.info("The user returned " + usersDeviceInfo != null ? usersDeviceInfo.toString() : null);
				return usersDeviceInfo;
			} finally {

			}
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addUserProfile(User user) throws ClassNotFoundException, SQLException {
		if (user.getUserType() != null && user.getUserType().size() > 0) {
			try {
				LOGGER.info("In adduser");
				UserTypeIds userTypeIds = new UserTypeIds();
				for (String type : user.getUserType()) {
					if (Constants.DISTRIBUTOR_TYPE.equals(type)) {
						String id = distributorRepository.addDistributor(user);
						userTypeIds.setDistributorId(id);
					}
					if (Constants.RETAILER_TYPE.equals(type)) {
						String id =  retailerRepository.addRetailer(user);
						userTypeIds.setRetailerId(id);
					}
					if (Constants.CUSTOMER_TYPE.equals(type)) {
						String id =  customerRepository.addCustomer(user);
						userTypeIds.setCustomerId(id);
					}
					if (Constants.MANUFACTURER_TYPE.equals(type)) {
						String id = manufacturerRepository.addManufacturer(user);
						userTypeIds.setManufacturerId(id);
					}
				}
				ObjectMapper mapperObj = new ObjectMapper();
				String jsonStr = mapperObj.writeValueAsString(userTypeIds);
				return jsonStr.replaceAll("\\\\", "");
			}catch (IOException e) {
				return Constants.ERROR_CONVERTING_IDS_STRING;
			}finally {

			}
		}
		return Constants.ERROR_USER_CREATION;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addCollectionAgent(CollectionAgent agent, String uid) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In addCollectionAgent");
			agent.setUid(Utils.generateRandomKey(50));
			String response = addUser(agent);
			response = collectionAgentRepository.addCollectionAgent(agent, uid);
			LOGGER.info("In addCollectionAgent response :" + response);
			return response;
		} finally {
		}
	}

	public User getUserByUsername(String username) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getUserByUsername " + username);
		String sql = "SELECT * FROM users WHERE username = ?";
		List<User> users = jdbcTemplate.query(sql, new Object[] { username }, new UserMapper());
		LOGGER.info("The user returned " + users != null ? users.toString() : null);
		return users != null ? users.size() > 0 ? users.get(0) : null : null;
	}
	
	class UserTypeIds {
		private String manufacturerId;
		private String distributorId;
		private String retailerId;
		private String customerId;
		private String collectionAgentId;
		
		public String getManufacturerId() {
			return manufacturerId;
		}
		public void setManufacturerId(String manufacturerId) {
			this.manufacturerId = manufacturerId;
		}
		public String getDistributorId() {
			return distributorId;
		}
		public void setDistributorId(String distributorId) {
			this.distributorId = distributorId;
		}
		public String getRetailerId() {
			return retailerId;
		}
		public void setRetailerId(String retailerId) {
			this.retailerId = retailerId;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getCollectionAgentId() {
			return collectionAgentId;
		}
		public void setCollectionAgentId(String collectionAgentId) {
			this.collectionAgentId = collectionAgentId;
		}
	}
}
