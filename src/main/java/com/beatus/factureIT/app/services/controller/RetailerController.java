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
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.model.ProductCategory;
import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.model.RetailerResponse;
import com.beatus.factureIT.app.services.model.UserTypeIdAndProducts;
import com.beatus.factureIT.app.services.service.RetailerService;
import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_RETAILER_REQUEST)
public class RetailerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetailerController.class);

	@Resource(name = "retailerService")
	private RetailerService retailerService;

	public static JSendResponse<RetailerResponse> jsend(RetailerResponse retailerResponse) {
		if (retailerResponse == null || retailerResponse.getRetailers() == null
				|| retailerResponse.getRetailers().get(0) == null) {
			return new JSendResponse<RetailerResponse>(Constants.FAILURE, retailerResponse);
		} else {
			return new JSendResponse<RetailerResponse>(Constants.SUCCESS, retailerResponse);
		}
	}
	
	public static JSendResponse<Retailer> jsend(Retailer retailer) {
		if (retailer == null || retailer == null) {
			return new JSendResponse<Retailer>(Constants.FAILURE, retailer);
		} else {
			return new JSendResponse<Retailer>(Constants.SUCCESS, retailer);
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

	@RequestMapping(value = Constants.WEB_RETAILER_GET_RETAILER_BY_ID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Retailer> getRetailerByIdGet(HttpServletRequest request,
			ModelMap model, @RequestParam String retailerId) throws ClassNotFoundException, SQLException {
		Retailer retailer = retailerService.getRetailerByRetailerId(retailerId);
		LOGGER.info("After the get call and the retailer is " + retailer != null
				? retailer.getFirstname() : "No Retailer data");
		return jsend(retailer);
	}

	@RequestMapping(value = Constants.WEB_RETAILER_GET_RETAILER_BY_UID, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<Retailer> getRetailerByUIDGet(HttpServletRequest request,
			ModelMap model, @RequestParam String uid) throws ClassNotFoundException, SQLException {
		Retailer retailer = retailerService.getRetailerByUID(uid);
		LOGGER.info("After the get call and the retailer is " + retailer != null
				? retailer.getFirstname() : "No Retailer data");
		return jsend(retailer);
	}

	@RequestMapping(value = Constants.WEB_RETAILER_GET_ALL_RETAILERS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<RetailerResponse> getAllRetailersGet(HttpServletRequest request,
			ModelMap model) throws ClassNotFoundException, SQLException {
		List<Retailer> retailers = retailerService.getAllRetailers();
		LOGGER.info("After the get call and the retailers are " + retailers != null
				? retailers.size() > 0 ? retailers.get(0).getFirstname() : "No Retailer data"
				: "No Retailer data");
		RetailerResponse resp = new RetailerResponse();
		resp.setRetailers(retailers);
		return jsend(resp);
	}
	
	@RequestMapping(value = Constants.WEB_RETAILER_GET_ALL_RETAILERS_BY_AREA, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<RetailerResponse> getAllRetailersInASpecificArea(HttpServletRequest request,
			ModelMap model, @RequestParam String latitude,  @RequestParam String longitude,  @RequestParam String radius) throws ClassNotFoundException, SQLException {
		List<Retailer> retailers = retailerService.getAllRetailersInASpecificArea(latitude, longitude, radius);
		LOGGER.info("After the get call and the retailers are " + retailers != null
				? retailers.size() > 0 ? retailers.get(0).getFirstname() : "No Retailer data"
				: "No Retailer data");
		RetailerResponse resp = new RetailerResponse();
		resp.setRetailers(retailers);
		return jsend(resp);
	}
	
	@RequestMapping(value = Constants.WEB_RETAILER_ADD_RETAILER, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addRetailerPost(HttpServletRequest request,
			ModelMap model, @RequestBody Retailer retailer) throws ClassNotFoundException, SQLException {
		String id = retailerService.addRetailer(retailer);
		LOGGER.info("After the add call and the retailer " + retailer != null
				? retailer.getFirstname() : "No Retailer data" + " got added successfully");
		return jsend(id);
	}
	
	@RequestMapping(value = Constants.WEB_RETAILER_EDIT_RETAILER, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> editRetailerPost(HttpServletRequest request,
			ModelMap model, @RequestBody Retailer retailer) throws ClassNotFoundException, SQLException {
		boolean isRetailerEdited = retailerService.editRetailer(retailer);
		LOGGER.info("After the edit call and the retailer " + retailer != null
				? retailer.getFirstname() : "No Retailer data" + " got edited successfully");
		return jsend(isRetailerEdited);
	}
	
	@RequestMapping(value = Constants.WEB_RETAILER_ADD_PRODUCTS, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addProductsForRetailerPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<Product> products, @RequestParam String retailerId) throws ClassNotFoundException, SQLException {
		boolean isProductsAdded = retailerService.addProductsForRetailer(products, retailerId);
		LOGGER.info("After the add products call for the retailer with Id: " + retailerId != null
				? retailerId : "No Retailer data");
		return jsend(isProductsAdded);
	}
	
	@RequestMapping(value = Constants.WEB_RETAILER_ADD_CATEGORIES, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addProductCategoriesForRetailerPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<ProductCategory> productCategories, @RequestParam String retailerId) throws ClassNotFoundException, SQLException {
		boolean isProductsAdded = retailerService.addProductCategoriesForRetailer(productCategories, retailerId);
		LOGGER.info("After the add product categories call for the retailer with Id: " + retailerId != null
				? retailerId : "No Retailer data");
		return jsend(isProductsAdded);
	}
	
	@RequestMapping(value = Constants.WEB_RETAILER_GET_PRODUCTS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getAllProductsOfRetailerGet(HttpServletRequest request,
			ModelMap model, @RequestParam String retailerId) throws ClassNotFoundException, SQLException {
		List<Product> retailerProducts = retailerService.getAllProductsOfRetailer(retailerId);
		LOGGER.info("After the get call for the products of retailers with ID:" + retailerId != null
				? retailerId : "No Retailer data");
		return jsend(retailerProducts);
	}
	
	@RequestMapping(value = Constants.WEB_RETAILER_GET_PRODUCT_CATEGORIES, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getAllProductCategoriesOfRetailerGet(HttpServletRequest request,
			ModelMap model, @RequestParam String retailerId) throws ClassNotFoundException, SQLException {
		List<ProductCategory> retailerProductCategories = retailerService.getAllProductCategoriesOfRetailer(retailerId);
		LOGGER.info("After the get call for the product categories of retailers with ID:" + retailerId != null
				? retailerId : "No Retailer data");
		return jsend(retailerProductCategories);
	}
	
	
	@RequestMapping(value = Constants.WEB_RETAILER_ADD_RELATED_DISTRIBUTORS, method = RequestMethod.POST)
	public @ResponseBody JSendResponse<String> addRetailerRelatedDistributorsPost(HttpServletRequest request,
			ModelMap model, @RequestBody List<UserTypeIdAndProducts> distributorAndProducts, @RequestParam String retailerId) throws ClassNotFoundException, SQLException {
		boolean isDistributorAdded = retailerService.addRetailerRelatedDistributors(distributorAndProducts, retailerId);
		LOGGER.info("After the add retailer related distributors with Id: " + retailerId != null
				? retailerId : "No Retailer data");
		return jsend(isDistributorAdded);
	}
	
	@RequestMapping(value = Constants.WEB_RETAILER_GET_RELATED_DISTRIBUTORS, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getRetailerRelatedDistributorsGet(HttpServletRequest request,
			ModelMap model,  @RequestParam String retailerId) throws ClassNotFoundException, SQLException {
		List<Distributor> distributors = retailerService.getRetailerRelatedDistributors(retailerId);
		LOGGER.info("After the get call for the distributors related to retailer with ID:" + retailerId != null
				? retailerId : "No Retailer data");
		return jsend(distributors);
	}
}
