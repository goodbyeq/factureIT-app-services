package com.beatus.factureIT.app.services.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("distributorRepository")
public class DistributorRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorRepository.class);

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addDistributor(User distributor) throws ClassNotFoundException, SQLException {

		PreparedStatement statement = null;
		Connection conn = null;
		try {
			LOGGER.info("In addDistributor");
			String sql = "INSERT INTO distributor (distributor_id, distributor_company_id, uid, distributor_company_name, dictributor_company_type, distributor_first_name, distributor_last_name, distributor_phone, distributor_email, distributor_address, distributor_city, distributor_state, distributor_zipcode ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = dataSource.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, Utils.generateRandomKey(50));
			statement.setString(2, distributor.getCompanyId());
			statement.setString(3, distributor.getUid());
			statement.setString(4, distributor.getCompanyName());
			statement.setString(5, distributor.getCompanyType());
			statement.setString(6, distributor.getFirstname());
			statement.setString(7, distributor.getLastname());
			statement.setString(8, distributor.getPhone());
			statement.setString(9, distributor.getEmail());
			statement.setString(10, distributor.getAddress());
			statement.setString(11, distributor.getCity());
			statement.setString(12, distributor.getState());
			statement.setString(13, distributor.getZipcode());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new distributor was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean editDistributor(User distributor) throws ClassNotFoundException, SQLException {
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			LOGGER.info("In editDistributor");
			String sql = "UPDATE distributor SET distributor_company_name = ?, dictributor_company_type = ?, distributor_first_name= ?, distributor_last_name= ?, distributor_phone= ?, distributor_email= ?, distributor_address= ?, distributor_city= ?, distributor_state = ?,  distributor_zipcode= ? WHERE distributor_id = ?";
			conn = dataSource.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, distributor.getCompanyName());
			statement.setString(2, distributor.getCompanyType());
			statement.setString(3, distributor.getFirstname());
			statement.setString(4, distributor.getLastname());
			statement.setString(5, distributor.getPhone());
			statement.setString(6, distributor.getEmail());
			statement.setString(7, distributor.getAddress());
			statement.setString(8, distributor.getCity());
			statement.setString(9, distributor.getState());
			statement.setString(10, distributor.getZipcode());
			statement.setString(11, distributor.getId());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A distributor was updated successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public User getDistributorById(String id) throws ClassNotFoundException, SQLException {
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			LOGGER.info("In getDistributorById");
			User distributor = null;
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist" + " WHERE distributor_id = ? ";
			conn = dataSource.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				distributor = new User();
				distributor.setId(result.getString("distributorId"));
				distributor.setCompanyName(result.getString("distributorCompanyName"));
				distributor.setCompanyType(result.getString("distributorCompanyType"));
				distributor.setCompanyId(result.getString("distributorCompanyId"));
				distributor.setUid(result.getString("uid"));
				distributor.setFirstname(result.getString("distributorFirstName"));
				distributor.setLastname(result.getString("distributorLastName"));
				distributor.setPhone(result.getString("distributorPhone"));
				distributor.setEmail(result.getString("distributorEmail"));
				distributor.setAddress(result.getString("distributorPhone"));
				distributor.setCity(result.getString("distributorAddress"));
				distributor.setState(result.getString("distributorCity"));
				distributor.setZipcode(result.getString("distributorState"));
				List<String> userType = new ArrayList<String>();
				userType.set(0, Constants.DISTRIBUTOR_TYPE);
				distributor.setUserType(userType);
			}
			return distributor;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<User> getAllDistributors() throws ClassNotFoundException, SQLException {
		List<User> distributors = new ArrayList<User>();
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			LOGGER.info("In getAllDistributors");
			User distributor = null;
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist";
			conn = dataSource.getConnection();
			statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				distributor = new User();
				distributor.setId(result.getString("distributorId"));
				distributor.setCompanyName(result.getString("distributorCompanyName"));
				distributor.setCompanyType(result.getString("distributorCompanyType"));
				distributor.setCompanyId(result.getString("distributorCompanyId"));
				distributor.setUid(result.getString("uid"));
				distributor.setFirstname(result.getString("distributorFirstName"));
				distributor.setLastname(result.getString("distributorLastName"));
				distributor.setPhone(result.getString("distributorPhone"));
				distributor.setEmail(result.getString("distributorEmail"));
				distributor.setAddress(result.getString("distributorPhone"));
				distributor.setCity(result.getString("distributorAddress"));
				distributor.setState(result.getString("distributorCity"));
				distributor.setZipcode(result.getString("distributorState"));
				List<String> userType = new ArrayList<String>();
				userType.set(0, Constants.DISTRIBUTOR_TYPE);
				distributor.setUserType(userType);
				distributors.add(distributor);
			}
			return distributors;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteDistributor(int distributorId, String companyId) throws SQLException {
		/*String sql = "DELETE FROM distributor where distributor_id = ?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, distributorId);
		statement.setString(2, companyId);

		boolean result = statement.execute();
		return result;*/
	}
}
