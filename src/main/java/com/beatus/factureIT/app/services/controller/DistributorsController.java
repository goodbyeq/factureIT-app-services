package com.beatus.factureIT.app.services.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.factureIT.app.services.model.Distributor;
import com.beatus.factureIT.app.services.model.DistributorResponse;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.model.ProductCategory;
import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.model.UserTypeIdAndProducts;
import com.beatus.factureIT.app.services.service.DistributorService;
import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_DISTRIBUTOR_REQUEST)
public class DistributorsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistributorsController.class);

	@Resource(name = "distributorService")
	private DistributorService distributorService;

	public static JSendResponse<DistributorResponse> jsend(DistributorResponse distributorResponse) {
		if (distributorResponse == null || distributorResponse.getDistributors() == null
				|| distributorResponse.getDistributors().get(0) == null) {
			return new JSendResponse<DistributorResponse>(Constants.FAILURE, distributorResponse);
		} else {
			return new JSendResponse<DistributorResponse>(Constants.SUCCESS, distributorResponse);
		}
	}
	
	public static JSendResponse<Distributor> jsend(Distributor distributor) {
		if (distributor == null || distributor == null) {
			return new JSendResponse<Distributor>(Constants.FAILURE, distributor);
		} else {
			return new JSendResponse<Distributor>(Constants.SUCCESS, distributor);
		}
	}
	
	public static JSendResponse<String> jsend(boolean response) {
		if (response) {
			return new JSendResponse<String>(Constants.SUCCESS, "Request processed Successfully");
		} else {
			return new JSendResponse<String>(Constants.FAILURE, "Request Processing failed");
		}
	}
	
	public static JSendResponse<String> jsend(String response) {
		if (response != null) {
			return new JSendResponse<String>(Constants.SUCCESS, "Request processed Successfully");
		} else {
			return new JSendResponse<String>(Constants.FAILURE, "Request Processing failed");
		}
	}
	
	public static JSendResponse<List<?>> jsend(List<?> response) {
		if (response == null || response == null) {
			return new JSendResponse<List<?>>(Constants.FAILURE, response);
		} else {
			return new JSendResponse<List<?>>(Constants.SUCCESS, response);
		}
	}

	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_DISTRIBUTOR_BY_ID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Distributor> getDistributorByIdGet(HttpServletRequest request,
			ModelMap model, @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		Distributor distributor = distributorService.getDistributorByDistributorId(distributorId);
		LOGGER.info("After the get call and the distributor is " + distributor != null
				? distributor.getFirstname() : "No Distributor data");
		return jsend(distributor);
	}

	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_DISTRIBUTOR_BY_UID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Distributor> getDistributorByUIDGet(HttpServletRequest request,
			ModelMap model, @RequestParam String uid) throws ClassNotFoundException, SQLException {
		Distributor distributor = distributorService.getDistributorByUID(uid);
		LOGGER.info("After the get call and the distributor is " + distributor != null
				? distributor.getFirstname() : "No Distributor data");
		return jsend(distributor);
	}

	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_ALL_DISTRIBUTORS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<DistributorResponse> getAllDistributorsGet(HttpServletRequest request,
			ModelMap model) throws ClassNotFoundException, SQLException {
		List<Distributor> distributors = distributorService.getAllDistributors();
		LOGGER.info("After the get call and the distributors are " + distributors != null
				? distributors.size() > 0 ? distributors.get(0).getFirstname() : "No Distributor data"
				: "No Distributor data");
		DistributorResponse resp = new DistributorResponse();
		resp.setDistributors(distributors);
		return jsend(resp);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_ALL_DISTRIBUTORS_BY_AREA, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<DistributorResponse> getAllDistributorsInASpecificArea(HttpServletRequest request,
			ModelMap model, @RequestParam String latitude,  @RequestParam String longitude,  @RequestParam String radius) throws ClassNotFoundException, SQLException {
		List<Distributor> distributors = distributorService.getAllDistributorsInASpecificArea(latitude, longitude, radius);
		LOGGER.info("After the get call and the distributors are " + distributors != null
				? distributors.size() > 0 ? distributors.get(0).getFirstname() : "No Distributor data"
				: "No Distributor data");
		DistributorResponse resp = new DistributorResponse();
		resp.setDistributors(distributors);
		return jsend(resp);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_DISTRIBUTOR, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addDistributorPost(HttpServletRequest request,
			ModelMap model, @RequestBody Distributor distributor) throws ClassNotFoundException, SQLException {
		String id = distributorService.addDistributor(distributor);
		LOGGER.info("After the add call and the distributor " + distributor != null
				? distributor.getFirstname() : "No Distributor data" + " got added successfully");
		return jsend(id);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_EDIT_DISTRIBUTOR, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> editDistributorPost(HttpServletRequest request,
			ModelMap model, @RequestBody Distributor distributor) throws ClassNotFoundException, SQLException {
		boolean isDistributorEdited = distributorService.editDistributor(distributor);
		LOGGER.info("After the edit call and the distributor " + distributor != null
				? distributor.getFirstname() : "No Distributor data" + " got edited successfully");
		return jsend(isDistributorEdited);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_PRODUCTS, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addProductsForDistributorPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<Product> products, @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		boolean isProductsAdded = distributorService.addProductsForDistributor(products, distributorId);
		LOGGER.info("After the add products call for the distributor with Id: " + distributorId != null
				? distributorId : "No Distributor data");
		return jsend(isProductsAdded);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_CATEGORIES, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addProductCategoriesForDistributorPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<ProductCategory> productCategories, @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		boolean isProductsAdded = distributorService.addProductCategoriesForDistributor(productCategories, distributorId);
		LOGGER.info("After the add product categories call for the distributor with Id: " + distributorId != null
				? distributorId : "No Distributor data");
		return jsend(isProductsAdded);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_PRODUCTS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getAllProductsOfDistributorGet(HttpServletRequest request,
			ModelMap model, @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		List<Product> distributorProducts = distributorService.getAllProductsOfDistributor(distributorId);
		LOGGER.info("After the get call for the products of distributors with ID:" + distributorId != null
				? distributorId : "No Distributor data");
		return jsend(distributorProducts);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_PRODUCT_CATEGORIES, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getAllProductCategoriesOfDistributorGet(HttpServletRequest request,
			ModelMap model, @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		List<ProductCategory> distributorProductCategories = distributorService.getAllProductCategoriesOfDistributor(distributorId);
		LOGGER.info("After the get call for the product categories of distributors with ID:" + distributorId != null
				? distributorId : "No Distributor data");
		return jsend(distributorProductCategories);
	}
	
	
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_RELATED_RETAILER, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addDistributorRelatedRetailersPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<UserTypeIdAndProducts> retailerAndProducts, @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		boolean isRetailerAdded = distributorService.addDistributorRelatedRetailers(retailerAndProducts, distributorId);
		LOGGER.info("After the add related retailers call for the distributor with Id: " + distributorId != null
				? distributorId : "No Distributor data");
		return jsend(isRetailerAdded);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_RELATED_RETAILER, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getDistributorRelatedRetailers(HttpServletRequest request,
			ModelMap model,  @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		List<Retailer> retailers = distributorService.getDistributorRelatedRetailers(distributorId);
		LOGGER.info("After the get call for the retailers related to distributors with ID:" + distributorId != null
				? distributorId : "No Distributor data");
		return jsend(retailers);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_RELATED_MANUFACTURER, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addDistributorRelatedManufacturersPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<UserTypeIdAndProducts> manufacturerAndProducts, @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		boolean isRetailerAdded = distributorService.addDistributorRelatedRetailers(manufacturerAndProducts, distributorId);
		LOGGER.info("After the add related manufacturers call for the distributor with Id: " + distributorId != null
				? distributorId : "No Distributor data");
		return jsend(isRetailerAdded);
	}
	
	@RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_RELATED_MANUFACTURER, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getDistributorRelatedManufacturers(HttpServletRequest request,
			ModelMap model,  @RequestParam String distributorId) throws ClassNotFoundException, SQLException {
		List<Retailer> retailers = distributorService.getDistributorRelatedRetailers(distributorId);
		LOGGER.info("After the get call for the manufacturers related to distributors with ID:" + distributorId != null
				? distributorId : "No Distributor data");
		return jsend(retailers);
	}
	
	
}
