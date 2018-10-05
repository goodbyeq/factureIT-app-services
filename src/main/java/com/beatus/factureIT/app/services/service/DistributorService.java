package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.model.CollectionAgent;
import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.model.ProductCategory;
import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.model.UserTypeIdAndProducts;
import com.beatus.factureIT.app.services.repository.DistributorRepository;

@Service
@Component("distributorService")
public class DistributorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorService.class);

	@Resource(name = "distributorRepository")
	private DistributorRepository distributorRepository;

	public Distributor getDistributorByDistributorId(String id) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributorByDistributorId");
		Distributor distributor = distributorRepository.getDistributorByDistributorId(id);
		return distributor;
	}
	
	public Distributor getDistributorByUID(String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributorByUID");
		Distributor distributor = distributorRepository.getDistributorByUID(uid);
		return distributor;
	}

	public List<Distributor> getAllDistributors() throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllDistributors");
		List<Distributor> distributors = distributorRepository.getAllDistributors();
		return distributors;
	}
	
	public String addDistributor(User distributor) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addDistributor");
		String id = distributorRepository.addDistributor(distributor);
		return id;
	}
	
	public boolean editDistributor(User distributor) throws ClassNotFoundException, SQLException {
		LOGGER.info("In editDistributor");
		boolean isDistributorEdited = distributorRepository.editDistributor(distributor);
		return isDistributorEdited;
	}
	
	public boolean addProductsForDistributor(List<Product> products, String distributorId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductsForDistributor");
		boolean isProductsAdded = distributorRepository.addProductsForDistributor(products, distributorId);
		return isProductsAdded;
	}
	
	public boolean addProductCategoriesForDistributor(List<ProductCategory> productCategories, String distributorId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductCategoriesForDistributor");
		boolean isCategoriesAdded = distributorRepository.addProductCategoriesForDistributor(productCategories, distributorId);
		return isCategoriesAdded;
	}

	public List<Product> getAllProductsOfDistributor(String distributorId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllProductsOfDistributor");
		List<Product> distributorProducts = distributorRepository.getAllProductsOfDistributor(distributorId);
		return distributorProducts;
	}
	
	public List<ProductCategory> getAllProductCategoriesOfDistributor(String distributorId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllProductCategoriesOfDistributor");
		List<ProductCategory> distributorProductCategories = distributorRepository.getAllProductCategoriesOfDistributor(distributorId);
		return distributorProductCategories;
	}
	
	public boolean addDistributorRelatedManufacturers(List<UserTypeIdAndProducts> manufacturerAndProducts, String distributorId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addDistributorRelatedManufacturers");
		boolean isRetailerAdded = distributorRepository.addDistributorRelatedRetailers(manufacturerAndProducts, distributorId);
		return isRetailerAdded;
	}
	
	public List<Retailer> getDistributorRelatedManufacturers(String distributorId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributorRelatedManufacturers");
		List<Retailer> retailers = distributorRepository.getDistributorRelatedRetailers(distributorId);
		return retailers;
	}
	
	
	public boolean addDistributorRelatedRetailers(List<UserTypeIdAndProducts> retailerAndProducts, String distributorId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addDistributorRelatedRetailers");
		boolean isRetailerAdded = distributorRepository.addDistributorRelatedRetailers(retailerAndProducts, distributorId);
		return isRetailerAdded;
	}
	
	public List<Retailer> getDistributorRelatedRetailers(String distributorId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getDistributorRelatedRetailers");
		List<Retailer> retailers = distributorRepository.getDistributorRelatedRetailers(distributorId);
		return retailers;
	}
	
	public void deleteDistributor(int distributorId, String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In deleteDistributor");
		distributorRepository.deleteDistributor(distributorId, companyId);
	}
	
	public List<Distributor> getAllDistributorsInASpecificArea(String latitude, String longitude, String radius) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllDistributorsInASpecificArea");
		List<Distributor> distributors = distributorRepository.getAllDistributorsInASpecificArea(latitude, longitude, radius);
		return distributors;
	}

}