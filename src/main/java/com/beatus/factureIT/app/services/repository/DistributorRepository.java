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

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.mapper.DistributorMapper;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("distributorRepository")
public class DistributorRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorRepository.class);
	
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public DistributorRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addDistributor(User distributor) throws ClassNotFoundException, SQLException {

		try {
			LOGGER.info("In addDistributor");
			String sql = "INSERT INTO distributor (distributor_id, distributor_company_id, uid, distributor_company_name, dictributor_company_type, distributor_first_name, distributor_last_name, distributor_phone, distributor_email, distributor_address, distributor_city, distributor_state, distributor_zipcode ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(sql, Utils.generateRandomKey(50), distributor.getCompanyId(), distributor.getUid(),
					distributor.getCompanyName(), distributor.getCompanyType(), distributor.getFirstname(), distributor.getLastname(), distributor.getPhone(), distributor.getEmail(), 
					distributor.getAddress(), distributor.getCity(), distributor.getState(), distributor.getZipcode());
		
			if (rowsInserted > 0) {
				LOGGER.info("A new distributor was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	public boolean editDistributor(User distributor) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In editDistributor");
			String sql = "UPDATE distributor SET distributor_company_name = ?, dictributor_company_type = ?, distributor_first_name= ?, distributor_last_name= ?, distributor_phone= ?, distributor_email= ?, distributor_address= ?, distributor_city= ?, distributor_state = ?,  distributor_zipcode= ? WHERE distributor_id = ?";
			
			int rowsInserted = jdbcTemplate.update(sql, distributor.getCompanyName(), distributor.getCompanyType(), distributor.getFirstname(),
					 distributor.getLastname(), distributor.getPhone(), distributor.getEmail(), 
					distributor.getAddress(), distributor.getCity(), distributor.getState(), distributor.getZipcode(), distributor.getId());
		
			if (rowsInserted > 0) {
				LOGGER.info("A distributor was updated successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	public Distributor getDistributorByDistributorId(String id) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getDistributorByDistributorId");
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist" + " WHERE distributor_id = ? ";
			Distributor distributor = jdbcTemplate.queryForObject(sql, new Object[] {  id }, new DistributorMapper());
			return distributor;
		} finally {
		}
	}

	public Distributor getDistributorByUID(String uid) throws ClassNotFoundException, SQLException {
		
		try {
			LOGGER.info("In getDistributorByDistributorId");
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist, users user" + " WHERE user.uid = ? AND dist.uid = user.uid ";
			Distributor distributor = jdbcTemplate.queryForObject(sql, new Object[] {  uid }, new DistributorMapper());
			return distributor;
		} finally {
		}
	}
	
	public List<Distributor> getAllDistributors() throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getAllDistributors");
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist";
			List<Distributor> distributors = jdbcTemplate.query(sql, new DistributorMapper());
			return distributors;
		} finally {
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
