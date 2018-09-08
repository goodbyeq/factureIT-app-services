package com.beatus.factureIT.app.services.service;


import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.exception.FactureITServiceException;
import com.beatus.factureIT.app.services.model.SmsVO;
import com.beatus.factureIT.app.services.repository.SMSRepository;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.Utils;

@Service("smsService")
public class SMSService {
	
	@Resource(name = "smsRepository")
	private SMSRepository smsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(SMSService.class);
	
	public String addSmsInfo(HttpServletRequest request, HttpServletResponse response, SmsVO smsVO)
			throws FactureITServiceException, SQLException {
		if (smsVO == null || smsVO.getUsername() == null) {
			throw new FactureITServiceException("Sms cant be sent.");
		}
		try {
			String smsId = Utils.generateRandomKey(50);
			smsVO.setSmsId(smsId);
			return smsRepository.addSMSInfo(smsVO);
		} catch (Exception exception) {
			LOGGER.error("sms Service Exception in the addSms() {} ",
					exception.getMessage());
			throw new FactureITServiceException("Exception while adding smsvo");
		}
	}

	public List<SmsVO> getSmsVOsByUID(String username) throws FactureITServiceException {
		LOGGER.info("In getSmsVOsByUID method of sms Service");
		try {
			if (StringUtils.isNotBlank(username)) {
				List<SmsVO> smsVOs = smsRepository.getSMSVOsByUID(username);
				if (smsVOs != null && smsVOs.size() > 0 ) {
					return smsVOs;
				} else
					return null;
			} else {
				LOGGER.error(
						" Exception in the getSmsVOsByUID() {},  uid passed cant be null or empty string");
				throw new FactureITServiceException("Sms Id passed cant be null or empty string");
			}
		} catch (Exception e) {
			throw new FactureITServiceException("Exception in getSmsVOsByUID " + e);
		}

	}
	
	public SmsVO getSmsVOByUIDAndSMSType(String username) throws FactureITServiceException{
		LOGGER.info("In getSmsVOByUIDAndSMSType method of Esms Service");
		try {
			if (StringUtils.isNotBlank(username)) {
				List<SmsVO> smsVOs = smsRepository.getSMSVOByUIDAndSMSType(username, Constants.SMS_VERIFICATION_TYPE);
				if (smsVOs != null && smsVOs.size() > 0) {
					return smsVOs.get(0);
				} else
					return null;
			} else {
				LOGGER.error(
						" Exception in the getSmsVOsByUID() {},  uid passed cant be null or empty string");
				throw new FactureITServiceException("Sms Id passed cant be null or empty string");
			}
		} catch (Exception e) {
			throw new FactureITServiceException("Exception in getSmsVOsByUID " + e);
		}

	}

	public String sendSms(HttpServletRequest request, HttpServletResponse response, SmsVO smsVO)
			throws FactureITServiceException {

		if (smsVO == null || smsVO.getUid() == null) {
			throw new FactureITServiceException("Sms cant be sent.");
		}
		try {
			
			addSmsInfo(request, response, smsVO);
		} catch (Exception e) {
			throw new FactureITServiceException("Error seding esms through AWS " + e.getMessage());
		}		
		return Constants.SUCCESS;
	}
	
	public String verifySendCode(String code, String uid) throws FactureITServiceException {
		if (StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(code)) {
			SmsVO smsVO = getSmsVOByUIDAndSMSType(uid);
			if (smsVO != null && smsVO.getSendCode() != null) {
				if (smsVO.getSendCode().equals(code)) {
					return Constants.SUCCESS;
				}
			}
		}
		return Constants.FAILURE;
	}

	/*public void getSMSScreen(HttpServletRequest request, ModelMap model, String companyId) throws ClassNotFoundException, SQLException {
		List<Product> products = productService.getProducts(companyId);
		Collections.sort(products);
		model.addAttribute("products", products);
		List<Location> locations = locationService.getLocations(companyId);
		Collections.sort(locations);
		model.addAttribute("locations", locations);
		List<ProductsAndLocations> productsAndLocations = productService.getProductsAndLocations(companyId);

		List<LocationAndPrice> locsAndPrices = new ArrayList<LocationAndPrice>();
		for (int i = 0; i < locations.size(); i++) {
			List<ProductAndPrice> productAndPrices = new ArrayList<ProductAndPrice>();
			for (int j = 0; j < productsAndLocations.size(); j++) {
				if (locations.get(i).getLocationName().equals(productsAndLocations.get(j).getProductLocationName())) {
					ProductAndPrice productAndPrice = new ProductAndPrice();
					productAndPrice.setProductName(productsAndLocations.get(j).getProductName());
					productAndPrice.setPrice(productsAndLocations.get(j).getProductPrice());
					productAndPrices.add(productAndPrice);
				}
			}
			Collections.sort(productAndPrices);
			LocationAndPrice locAndPrice = new LocationAndPrice();
			locAndPrice.setLocationName(locations.get(i).getLocationName());
			locAndPrice.setProductAndPrices(productAndPrices);
			locsAndPrices.add(locAndPrice);
		}
		Collections.sort(locsAndPrices);
		model.addAttribute("locsAndPrices", locsAndPrices);
	}

	public void postSMSScreen(HttpServletRequest request, ProductWithLocationsAndPricesRequest productNameLocAndPrice,
			ModelMap model, String companyId, String uid) throws ClassNotFoundException, SQLException {

		if (productNameLocAndPrice != null) {
			if (productNameLocAndPrice.getProductNameLocAndPrice() != null
					&& productNameLocAndPrice.getProductNameLocAndPrice().size() > 0) {
				List<String> productLocAndPrice = productNameLocAndPrice.getProductNameLocAndPrice();
				Map<String, List<ProductAndPrice>> productsUpdated = new HashMap<String, List<ProductAndPrice>>();
				for (int i = 0; i < productLocAndPrice.size(); i++) {
					String[] productLocationValue = productLocAndPrice.get(i).split("##");
					if (productLocationValue != null && productLocationValue.length == 3) {
						List<ProductAndPrice> list = null;
						if (productsUpdated.containsKey(productLocationValue[0])) {
							list = productsUpdated.get(productLocationValue[0]);
						} else {
							list = new ArrayList<ProductAndPrice>();
						}
						ProductAndPrice product = new ProductAndPrice();
						product.setProductName(productLocationValue[1]);
						//product.setPrice(productLocationValue[2]);
						list.add(product);
						productsUpdated.put(productLocationValue[0], list);
					}
				}
				List<Distributor> distributors = distributorService.getDistributors(companyId);
				int smsCount = 0;
				for (Distributor distributor : distributors) {
					if (distributor != null && productsUpdated.containsKey(distributor.getLocationId())) {
						List<ProductAndPrice> products = productsUpdated.get(distributor.getLocationId());
						SMSConfiguration config = getSMSScreenConfiguration(model, companyId);
						String productsInfo = config.getMessageHeader();
						for (ProductAndPrice product : products) {
							if (product != null) {
								productsInfo += "The product " + product.getProductName() + " price changed to "
										+ product.getPrice() + "\n";
							}
						}
						productsInfo = productsInfo + "\n "+ config.getMessageFooter();
						LOGGER.info(productsInfo);
						UrlBuilder urlBuilder = new UrlBuilder(config.getSmsUrl());
						Map<String, Object> requestParameters = new HashMap<>();
						requestParameters.put("uname", config.getParameterUsername());
						requestParameters.put("pass", config.getParameterPassword());
						requestParameters.put("send", config.getSendCode());
						requestParameters.put("dest", distributor.getDistributorPhone());
						requestParameters.put("msg", productsInfo);

						urlBuilder.add(requestParameters);

						LOGGER.debug("Sending request with params: [{}]", urlBuilder.getUrl());

						String response = "";
						try {
							response = new HttpApiCall(5000, 5000).get(urlBuilder.toString());
							if(response.contains(Constants.OK)){
								smsCount += smsCount;
							}
							LOGGER.debug("responded with [{}]", response);
						} catch (IOException ioe) {
							LOGGER.error(ioe.toString());
						}
					}
				}
				// TO-DO update smsCount in database and also specify which SMS not sent on the UI.
			}
		}
	}

	public String addSMSScreenConfiguration(HttpServletRequest request, SMSConfiguration smsConfiguration,
			ModelMap model, String companyId, String uid) throws ClassNotFoundException, SQLException {
		smsConfiguration.setCompanyId(companyId);
		smsConfiguration.setUid(uid);
		smsRepository.addSMSConfiguration(smsConfiguration);
		return Constants.REDIRECT + "/sms/getSmsConfiguration";
	}


	public SMSConfiguration getSMSScreenConfiguration(ModelMap model, String companyId) throws ClassNotFoundException, SQLException {
		SMSConfiguration config = smsRepository.getSMSConfiguration(companyId);
		model.addAttribute("configuration", config);
		return config;
	}

	public String editSMSScreenConfiguration(HttpServletRequest request, SMSConfiguration smsConfiguration,
			ModelMap model, String companyId, String uid) throws SQLException {
		smsConfiguration.setCompanyId(companyId);
		smsConfiguration.setUid(uid);
		smsRepository.editSMSConfiguration(smsConfiguration);
		return Constants.REDIRECT + "/sms/getSmsConfiguration";
	}
*/
}
