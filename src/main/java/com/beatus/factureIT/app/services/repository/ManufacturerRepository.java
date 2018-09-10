package com.beatus.factureIT.app.services.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beatus.factureIT.app.services.model.CollectionAgent;
import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.Manufacturer;
import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.model.ProductCategory;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.mapper.DistributorMapper;
import com.beatus.factureIT.app.services.model.mapper.ManufacturerMapper;
import com.beatus.factureIT.app.services.model.mapper.ManufacturerProductCategoryMapper;
import com.beatus.factureIT.app.services.model.mapper.ManufacturerProductMapper;
import com.beatus.factureIT.app.services.utils.Utils;

@Repository("manufacturerRepository")
public class ManufacturerRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerRepository.class);

	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public ManufacturerRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public String addManufacturer(User manufacturer) throws ClassNotFoundException, SQLException {

		try {
			LOGGER.info("In addManufacturer");
			String id = Utils.generateRandomKey(50);
			String sql = "INSERT INTO manufacturer (manufacturer_id, manufacturer_company_id, uid, manufacturer_company_name, manufacturer_company_type, manufacturer_first_name, manufacturer_last_name, manufacturer_phone, manufacturer_email, manufacturer_address, manufacturer_city, manufacturer_state, manufacturer_zipcode, latitude, longitude ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int rowsInserted = jdbcTemplate.update(sql, id, Utils.generateRandomKey(50),
					manufacturer.getUid(), manufacturer.getCompanyName(), manufacturer.getCompanyType(),
					manufacturer.getFirstname(), manufacturer.getLastname(), manufacturer.getPhone(),
					manufacturer.getEmail(), manufacturer.getAddress(), manufacturer.getCity(), manufacturer.getState(),
					manufacturer.getZipcode(), manufacturer.getLatitude(), manufacturer.getLongitude());

			if (rowsInserted > 0) {
				LOGGER.info("A new manufacturer was inserted successfully!");
				return id;
			} else {
				return null;
			}
		} finally {
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean editManufacturer(User manufacturer) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In editManufacturer");
			String sql = "UPDATE manufacturer SET manufacturer_company_name = ?, dictributor_company_type = ?, manufacturer_first_name= ?, manufacturer_last_name= ?, manufacturer_phone= ?, manufacturer_email= ?, manufacturer_address= ?, manufacturer_city= ?, manufacturer_state = ?,  manufacturer_zipcode= ? WHERE manufacturer_id = ?";

			int rowsInserted = jdbcTemplate.update(sql, manufacturer.getCompanyName(), manufacturer.getCompanyType(),
					manufacturer.getFirstname(), manufacturer.getLastname(), manufacturer.getPhone(),
					manufacturer.getEmail(), manufacturer.getAddress(), manufacturer.getCity(), manufacturer.getState(),
					manufacturer.getZipcode(), manufacturer.getId());

			if (rowsInserted > 0) {
				LOGGER.info("A manufacturer was updated successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public Manufacturer getManufacturerByManufacturerId(String id) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getManufacturerByManufacturerId");
			String sql = "SELECT dist.manufacturer_id AS manufacturerId, dist.manufacturer_company_name AS manufacturerCompanyName, "
					+ " dist.manufacturer_company_type AS manufacturerCompanyType, dist.manufacturer_company_id AS manufacturerCompanyId, dist.uid AS uid, "
					+ " dist.manufacturer_first_name AS manufacturerFirstName, dist.manufacturer_last_name AS manufacturerLastName, "
					+ " dist.manufacturer_phone AS manufacturerPhone, dist.manufacturer_email AS manufacturerEmail, dist.manufacturer_address AS manufacturerAddress, "
					+ " dist.manufacturer_city AS manufacturerCity, dist.manufacturer_state AS manufacturerState, dist.manufacturer_zipcode AS manufacturerZipcode, "
					+ " u.username AS username, u.user_type AS userType, u.verified AS verified "
					+ " FROM manufacturer dist, users u WHERE dist.uid = u.uid AND dist.manufacturer_id = ? ";
			Manufacturer manufacturer = jdbcTemplate.queryForObject(sql, new Object[] { id }, new ManufacturerMapper());
			return manufacturer;
		} finally {
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public Manufacturer getManufacturerByUID(String uid) throws ClassNotFoundException, SQLException {

		try {
			LOGGER.info("In getManufacturerByManufacturerId");
			String sql = "SELECT dist.manufacturer_id AS manufacturerId, dist.manufacturer_company_name AS manufacturerCompanyName, "
					+ " dist.manufacturer_company_type AS manufacturerCompanyType, dist.manufacturer_company_id AS manufacturerCompanyId, dist.uid AS uid, "
					+ " dist.manufacturer_first_name AS manufacturerFirstName, dist.manufacturer_last_name AS manufacturerLastName, "
					+ " dist.manufacturer_phone AS manufacturerPhone, dist.manufacturer_email AS manufacturerEmail, dist.manufacturer_address AS manufacturerAddress, "
					+ " dist.manufacturer_city AS manufacturerCity, dist.manufacturer_state AS manufacturerState, dist.manufacturer_zipcode AS manufacturerZipcode, "
					+ " u.username AS username, u.user_type AS userType, u.verified AS verified "
					+ " FROM manufacturer dist, users u WHERE dist.uid = u.uid AND u.uid = ? ";
			Manufacturer manufacturer = jdbcTemplate.queryForObject(sql, new Object[] { uid },
					new ManufacturerMapper());
			return manufacturer;
		} finally {
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<Manufacturer> getAllManufacturers() throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getAllManufacturers");
			String sql = "SELECT dist.manufacturer_id AS manufacturerId, dist.manufacturer_company_name AS manufacturerCompanyName, "
					+ " dist.manufacturer_company_type AS manufacturerCompanyType, dist.manufacturer_company_id AS manufacturerCompanyId, dist.uid AS uid, "
					+ " dist.manufacturer_first_name AS manufacturerFirstName, dist.manufacturer_last_name AS manufacturerLastName, "
					+ " dist.manufacturer_phone AS manufacturerPhone, dist.manufacturer_email AS manufacturerEmail, dist.manufacturer_address AS manufacturerAddress, "
					+ " dist.manufacturer_city AS manufacturerCity, dist.manufacturer_state AS manufacturerState, dist.manufacturer_zipcode AS manufacturerZipcode, "
					+ " u.username AS username, u.user_type AS userType, u.verified AS verified "
					+ " FROM manufacturer dist, users u WHERE dist.uid = u.uid";
			List<Manufacturer> manufacturers = jdbcTemplate.query(sql, new ManufacturerMapper());
			return manufacturers;
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<Manufacturer> getAllManufacturersInASpecificArea(String latitude, String longitude, String radius) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getAllManufacturersInASpecificArea");
			String sql = "SELECT dist.manufacturer_id AS manufacturerId, dist.manufacturer_company_name AS manufacturerCompanyName, "
					+ " dist.manufacturer_company_type AS manufacturerCompanyType, dist.manufacturer_company_id AS manufacturerCompanyId, dist.uid AS uid, "
					+ " dist.manufacturer_first_name AS manufacturerFirstName, dist.manufacturer_last_name AS manufacturerLastName, "
					+ " dist.manufacturer_phone AS manufacturerPhone, dist.manufacturer_email AS manufacturerEmail, dist.manufacturer_address AS manufacturerAddress, "
					+ " dist.manufacturer_city AS manufacturerCity, dist.manufacturer_state AS manufacturerState, dist.manufacturer_zipcode AS manufacturerZipcode, "
					+ " u.username AS username, u.user_type AS userType, u.verified AS verified "
					+ " FROM manufacturer dist, users u WHERE dist.uid = u.uid AND earth_box( ll_to_earth(" + latitude + ", " + longitude + "), " +radius +") @> ll_to_earth(dist.latitude, dist.longitude)";
			List<Manufacturer> manufacturers = jdbcTemplate.query(sql, new ManufacturerMapper());
			return manufacturers;
		} finally {
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addProductsForManufacturer(List<Product> products, String manufacturerId) {
		try {
			LOGGER.info("In addProductForManufacture");
			String sql = "INSERT INTO manufacturer_product (manufacturer_product_id, manufacturer_id, product_name, product_desc, product_category_id, brand_name, hsn_code, product_image, product_price, product_unit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int[] rowsInserted = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Product product = products.get(i);
					ps.setString(1, Utils.generateRandomKey(50));
					ps.setString(2, manufacturerId);
					ps.setString(3, product.getProductName());
					ps.setString(4, product.getProductDesc());					
					ps.setString(5, product.getProductCategoryId());
					ps.setString(6, product.getBrandName());
					ps.setString(7, product.getHsnCode());
					ps.setString(8, product.getProductImageString());
					ps.setString(9, product.getPrice());
					ps.setString(10, product.getUnit());
				}

				@Override
				public int getBatchSize() {
					return products.size();
				}
			});
			if (rowsInserted.length > 0) {
				LOGGER.info("A new manufacturer was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addProductCategoriesForManufacturer(List<ProductCategory> productCategories, String manufacturerId) {
		try {
			LOGGER.info("In addProductCategoriesForManufacturer");
			String sql = "INSERT INTO manufacturer_product_category (manufacturer_product_category_id, manufacturer_id, product_category_name, product_category_desc) VALUES (?, ?, ?, ?)";
			int[] rowsInserted = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ProductCategory productCategory = productCategories.get(i);
					ps.setString(1, Utils.generateRandomKey(50));
					ps.setString(2, manufacturerId);
					ps.setString(3, productCategory.getProductCategoryName());
					ps.setString(4, productCategory.getProductCategoryDesc());					
				}

				@Override
				public int getBatchSize() {
					return productCategories.size();
				}
			});
			if (rowsInserted.length > 0) {
				LOGGER.info("A new manufacturer was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<Product> getAllProductsOfManufacturer(String manufacturerId){
		try {
			LOGGER.info("In getAllProductsOfManufacturer");
			String sql = "SELECT manPro.manufacturer_product_id AS productId, manPro.manufacturer_id AS manufacturerId, "
					+ " manPro.product_name AS productName, manPro.product_desc AS productDesc, manPro.product_category_id AS productCategoryId, "
					+ " manPro.brand_name AS brandName, manPro.hsn_code AS hsnCode, "
					+ " manPro.product_image AS productImage "
					+ " FROM manufacturer_product manPro WHERE manPro.manufacturer_id = ?";
			List<Product> manufacturerProducts = jdbcTemplate.query(sql, new Object[] {manufacturerId},  new ManufacturerProductMapper());
			return manufacturerProducts;
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<ProductCategory> getAllProductCategoriesOfManufacturer(String manufacturerId){
		try {
			LOGGER.info("In getAllProductsOfManufacturer");
			String sql = "SELECT manProCat.manufacturer_product_category_id AS productCategoryId, manProCat.manufacturer_id AS manufacturerId, "
					+ " manProCat.product_category_name AS productCategoryName, manProCat.product_category_desc AS productCategoryDesc "
					+ " FROM manufacturer_product_category manProCat WHERE manProCat.manufacturer_id = ?";
			List<ProductCategory> manufacturerProductCategories = jdbcTemplate.query(sql, new Object[] {manufacturerId},  new ManufacturerProductCategoryMapper());
			return manufacturerProductCategories;
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean addManufacturerRelatedDistributors(List<String> distributorIds, String manufacturerId) {
		try {
			LOGGER.info("In addProductCategoriesForManufacturer");
			String sql = "INSERT INTO manufacturer_distributors (manufacturer_distributor_id, manufacturer_id, distributor_id) VALUES (?, ?, ?)";
			int[] rowsInserted = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, Utils.generateRandomKey(50));
					ps.setString(2, manufacturerId);
					ps.setString(3, distributorIds.get(i));
				}

				@Override
				public int getBatchSize() {
					return distributorIds.size();
				}
			});
			if (rowsInserted.length > 0) {
				LOGGER.info("A new manufacturer was inserted successfully!");
				return true;
			} else {
				return false;
			}
		} finally {
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
	public List<Distributor> getManufacturerRelatedDistributors(String manufacturerId) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("In getManufacturerRelatedDistributors");
			String sql = "SELECT dist.distributor_id AS distributorId, dist.distributor_company_name AS distributorCompanyName, "
					+ " dist.distributor_company_type AS distributorCompanyType, dist.distributor_company_id AS distributorCompanyId, dist.uid AS uid, "
					+ " dist.distributor_first_name AS distributorFirstName, dist.distributor_last_name AS distributorLastName, "
					+ " dist.distributor_phone AS distributorPhone, dist.distributor_email AS distributorEmail, dist.distributor_address AS distributorAddress, "
					+ " dist.distributor_city AS distributorCity, dist.distributor_state AS distributorState, dist.distributor_zipcode AS distributorZipcode "
					+ " FROM distributor dist, manufacturer_distributors md WHERE md.manufacturer_id = ? AND md.distributor_id = dist.distributor_id";
			List<Distributor> distributors = jdbcTemplate.query(sql, new Object[] {manufacturerId}, new DistributorMapper());
			return distributors;
		} finally {
		}
	}
	
	public void deleteManufacturer(int manufacturerId, String companyId) throws SQLException {
		/*
		 * String sql = "DELETE FROM manufacturer where manufacturer_id = ?";
		 * 
		 * PreparedStatement statement = conn.prepareStatement(sql); statement.setInt(1,
		 * manufacturerId); statement.setString(2, companyId);
		 * 
		 * boolean result = statement.execute(); return result;
		 */
	}

}
