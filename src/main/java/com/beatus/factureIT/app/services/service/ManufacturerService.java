package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.UserTypeIdAndProducts;
import com.beatus.factureIT.app.services.model.Manufacturer;
import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.model.ProductCategory;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.repository.ManufacturerRepository;

@Service
@Component("manufacturerService")
public class ManufacturerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerService.class);

	@Resource(name = "manufacturerRepository")
	private ManufacturerRepository manufacturerRepository;

	public Manufacturer getManufacturerByManufacturerId(String id) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getManufacturerByManufacturerId");
		Manufacturer manufacturer = manufacturerRepository.getManufacturerByManufacturerId(id);
		return manufacturer;
	}
	
	public Manufacturer getManufacturerByUID(String uid) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getManufacturerByUID");
		Manufacturer manufacturer = manufacturerRepository.getManufacturerByUID(uid);
		return manufacturer;
	}

	public List<Manufacturer> getAllManufacturers() throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllManufacturers");
		List<Manufacturer> manufacturers = manufacturerRepository.getAllManufacturers();
		return manufacturers;
	}
	
	public String addManufacturer(User manufacturer) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addManufacturer");
		String id = manufacturerRepository.addManufacturer(manufacturer);
		return id;
	}
	
	public boolean editManufacturer(User manufacturer) throws ClassNotFoundException, SQLException {
		LOGGER.info("In editManufacturer");
		boolean isManufacturerEdited = manufacturerRepository.editManufacturer(manufacturer);
		return isManufacturerEdited;
	}
	
	public boolean addProductsForManufacturer(List<Product> products, String manufacturerId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductsForManufacturer");
		boolean isProductsAdded = manufacturerRepository.addProductsForManufacturer(products, manufacturerId);
		return isProductsAdded;
	}
	
	public boolean addProductCategoriesForManufacturer(List<ProductCategory> productCategories, String manufacturerId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addProductCategoriesForManufacturer");
		boolean isCategoriesAdded = manufacturerRepository.addProductCategoriesForManufacturer(productCategories, manufacturerId);
		return isCategoriesAdded;
	}

	public List<Product> getAllProductsOfManufacturer(String manufacturerId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllProductsOfManufacturer");
		List<Product> manufacturerProducts = manufacturerRepository.getAllProductsOfManufacturer(manufacturerId);
		return manufacturerProducts;
	}
	
	public List<ProductCategory> getAllProductCategoriesOfManufacturer(String manufacturerId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllProductCategoriesOfManufacturer");
		List<ProductCategory> manufacturerProductCategories = manufacturerRepository.getAllProductCategoriesOfManufacturer(manufacturerId);
		return manufacturerProductCategories;
	}
	
	public boolean addManufacturerRelatedDistributors(List<UserTypeIdAndProducts> distributorAndProducts, String manufacturerId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addManufacturerRelatedDistributors");
		boolean isDistributorAdded = manufacturerRepository.addManufacturerRelatedDistributors(distributorAndProducts, manufacturerId);
		return isDistributorAdded;
	}
	
	public List<Distributor> getManufacturerRelatedDistributors(String manufacturerId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getManufacturerRelatedDistributors");
		List<Distributor> distributors = manufacturerRepository.getManufacturerRelatedDistributors(manufacturerId);
		return distributors;
	}
	
	public void deleteManufacturer(int manufacturerId, String companyId) throws ClassNotFoundException, SQLException {
		LOGGER.info("In deleteManufacturer");
		manufacturerRepository.deleteManufacturer(manufacturerId, companyId);
	}
	
	public List<Manufacturer> getAllManufacturersInASpecificArea(String latitude, String longitude, String radius) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getAllManufacturersInASpecificArea");
		List<Manufacturer> manufacturers = manufacturerRepository.getAllManufacturersInASpecificArea(latitude, longitude, radius);
		return manufacturers;
	}
	
}